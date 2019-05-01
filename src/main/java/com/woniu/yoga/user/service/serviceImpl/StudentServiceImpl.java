package com.woniu.yoga.user.service.serviceImpl;

import com.woniu.yoga.commom.utils.Attributes;
import com.woniu.yoga.commom.utils.OrderIdUtil;
import com.woniu.yoga.commom.utils.UserLevelUtil;
import com.woniu.yoga.communicate.dao.CommentMapper;
import com.woniu.yoga.communicate.pojo.Comment;
import com.woniu.yoga.manage.dao.CouponMapper;
import com.woniu.yoga.manage.pojo.Coupon;
import com.woniu.yoga.pay.dao.WalletMapper;
import com.woniu.yoga.pay.dao.WalletRecordMapper;
import com.woniu.yoga.pay.pojo.Wallet;
import com.woniu.yoga.pay.pojo.WalletRecord;
import com.woniu.yoga.user.dao.*;
import com.woniu.yoga.user.dto.SearchConditionDTO;
import com.woniu.yoga.user.pojo.*;
import com.woniu.yoga.user.service.StudentService;
import com.woniu.yoga.user.util.ConvertVOToDTOUtil;
import com.woniu.yoga.user.util.GetBmapDistanceUtil;
import com.woniu.yoga.user.util.OrderUtil;
import com.woniu.yoga.user.util.ResultUtil;
import com.woniu.yoga.user.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author liufeng
 * @ClassName StudentService
 * @Date 2019/4/18 15:30
 * @Version 1.0
 * @Description 处理学员和后台的交互
 **/
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CoachMapper coachMapper;
    @Autowired
    private WalletMapper walletMapper;
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private CouponMapper couponMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private WalletRecordMapper walletRecordMapper;
    @Autowired
    private RedisTemplate redisTemplate;



    //如果是教练，查找头像、姓名、简介、流派、认证方式（单击查看场馆）、课程（单击查看课程），交易次数，好评数
    //好友或者公开才能查看qq、微信、电话等信息
    @Override
    public Result getDetailInfoByUserId(Integer userId, Integer coachId) {
        if (coachId == null) {
            return ResultUtil.errorOperation("请选择想了解的瑜伽师!");
        }
        CoachDetailInfoVO coachDetailInfoVO = null;
        try {
            coachDetailInfoVO = userMapper.getDetailInfoByUserId(coachId);
            //如果是场馆认证，设置venueName：场馆名
            if (coachDetailInfoVO.getAuthentication() == 1) {
                coachDetailInfoVO.setVenueName(coachMapper.getVenueByCoachId(coachId));
            }
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
        //如果学员和瑜伽师不是好友，隐藏个人信息；或者瑜伽师设置保密
        if ((coachDetailInfoVO.getPrivacy() == 0) || (coachDetailInfoVO.getPrivacy() == 1 && !("学员和瑜伽师是好友" == ""))) {
            coachDetailInfoVO.setQq(null);
            coachDetailInfoVO.setWechat(null);
            coachDetailInfoVO.setPhone(null);
        }
        //如果是官方认证，设置venueName：平台认证
        if (coachDetailInfoVO.getAuthentication() == 2) {
            coachDetailInfoVO.setVenueName("平台认证");
        }
        return ResultUtil.actionSuccess("查询成功", coachDetailInfoVO);
    }

    //学员下单
    @Override
    public Result saveOrder(Integer userId, Order order) {
        try {
            order.setPayerId(userId);
            Wallet wallet = walletMapper.selectByPrimaryKey(order.getPayerId());
            Course course = courseMapper.selectByPrimaryKey(order.getCourse().getCourseId());
            BigDecimal orderMoney = course.getCoursePrice().multiply(new BigDecimal(order.getCourseCount()));
            if (orderMoney.compareTo(wallet.getBalance()) < 0) {
                return ResultUtil.errorOperation("余额不足，请充值");
            }
            order.setOrderMoney(orderMoney);
            order.setOrderStatus(OrderUtil.NEWORDER);
            order.setOrderId(OrderIdUtil.getOrderId());
            order.setCreateTime(new Date());
            //插入订单，
            orderMapper.insertSelective(order);
            return ResultUtil.actionSuccess("已下单，等待处理中...", order);
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    //学员付款前的订单金额更新，返回订单确认信息
    @Override
    public Result updateOrderWithCoupon(Integer userId, String orderId, Integer couponId) {
        try {
            Order order = orderMapper.selectByPrimaryKey(orderId);
            if (order.getPayerId() != userId) {
                return ResultUtil.illegalOperation();
            }
            if (order.getOrderStatus() != OrderUtil.WAITTOPAY) {
                return ResultUtil.errorOperation("订单状态错误，请联系管理员");
            }
            int faceValue = 0;
            if (couponId != null) {
                Coupon coupon = couponMapper.selectByPrimaryKey(couponId);
                if (coupon == null) {
                    return ResultUtil.errorOperation("优惠券状态错误，请联系管理员");
                }
                faceValue = coupon.getFaceValue();
            }

            User user = userMapper.selectByPrimaryKey(order.getPayerId());
            //查询会员等级，打折，计算应付款
            BigDecimal VIPDiscount = userMapper.selectDiscountByLevel(user.getUserLevel());
            BigDecimal discount = order.getOrderMoney().multiply(VIPDiscount).subtract(new BigDecimal(String.valueOf(faceValue)));
            order.setDiscount(discount);
            order.setCouponId(couponId);
            orderMapper.updateByPrimaryKeySelective(order);
            return ResultUtil.actionSuccess("订单信息更新，请确认...", order);
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    //学员钱包余额减少，瑜伽师（或所在场馆）余额增加，添加钱包记录，更改订单状态、优惠券状态、
    @Override
    public Result updateOrderForPay(Integer userId, String orderId) {
        try {
            Order order = orderMapper.selectByPrimaryKey(orderId);
            if (order.getPayerId() != userId) {
                return ResultUtil.illegalOperation();
            }
            if (order.getOrderStatus() != OrderUtil.WAITTOPAY) {
                return ResultUtil.errorOperation("订单状态错误，请联系管理员");
            }
            User user = userMapper.selectByPrimaryKey(order.getPayerId());
            Wallet studentWallet = walletMapper.findWalletByUserId(user.getUserId());
            //更新钱包余额
            if (studentWallet.getBalance().compareTo(order.getDiscount()) > 0) {
                studentWallet.setBalance(studentWallet.getBalance().subtract(order.getDiscount()));
            } else {
                return ResultUtil.errorOperation("余额不足");
            }
            //添加钱包记录：共2条；1：学员付款给平台；2：平台付款给瑜伽师或教练；
            WalletRecord studentPayCourseRecord = this.getStudentPayCourseRecord(order);
            WalletRecord platfotmPayCourseRecord = this.getPlatfotmPayCourseRecord(order);
            WalletRecord coachCourseRecord = this.getCoachCourseRecord(order);
            platfotmPayCourseRecord.setFromId(Attributes.PLATFORMNUMBER);
            //更新瑜伽师（场馆）钱包余额
            Wallet acceptWallet = walletMapper.selectByPrimaryKey(platfotmPayCourseRecord.getToId());
            acceptWallet.setBalance(acceptWallet.getBalance().add(platfotmPayCourseRecord.getMoney()));
            //更新订单状态
            order.setOrderStatus(OrderUtil.PAIED);
            //更新优惠券状态
            if (order.getCouponId() != null) {
                Coupon coupon = couponMapper.selectByPrimaryKey(order.getCouponId());
                coupon.setCouponStatus(2);//0未使用；1可用；2使用；3过期
                couponMapper.updateByPrimaryKeySelective(coupon);
            }
            //保存钱包余额
            walletMapper.updateByPrimaryKeySelective(studentWallet);
            walletMapper.updateByPrimaryKeySelective(acceptWallet);
            walletRecordMapper.insertSelective(studentPayCourseRecord);
            walletRecordMapper.insertSelective(platfotmPayCourseRecord);
            walletRecordMapper.insertSelective(coachCourseRecord);
            return ResultUtil.actionSuccess("支付成功", order);
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public Result saveComment(Integer userId, String orderId, Comment comment) {
        if (comment.getEntityType() == 1) {
            return ResultUtil.illegalOperation();
        }
        try {
            commentMapper.insertSelective(comment);
            //学员、瑜伽师 会员等级，积分变化，交易记录，并返回评论详细信息
            //根据金额转换积分，修改积分
            //根据积分转换等级
            User student = userMapper.selectByPrimaryKey(comment.getUserId());
            if (userId != student.getUserId()) {
                ResultUtil.illegalOperation();
            }
            student.setUserScore(student.getUserScore() + Attributes.INTEGRALADDWITHCOMMENT);
            student.setUserLevel(UserLevelUtil.getUserLevel(student.getUserScore()));
            Coach coach = coachMapper.selectByCourseId(comment.getEntityId());
            if (comment.getCommentDegree() == 0) {
                coach.setGoodComment(coach.getGoodComment() + 1);
            }
            if (comment.getCommentDegree() == 1) {
                coach.setCommonComment(coach.getCommonComment() + 1);
            }
            if (comment.getCommentDegree() == 2) {
                coach.setBadComment(coach.getBadComment() + 1);
            }
            User cUser = userMapper.selectByPrimaryKey(coach.getUserId());
            cUser.setUserScore(cUser.getUserScore() + Attributes.INTEGRALADDWITHCOMMENT);
            cUser.setUserLevel(UserLevelUtil.getUserLevel(cUser.getUserScore()));
            coachMapper.updateByPrimaryKeySelective(coach);
            userMapper.updateByPrimaryKeySelective(student);
            userMapper.updateByPrimaryKeySelective(cUser);
            orderMapper.updateStatusByOrderId(orderId, OrderUtil.END);
            return ResultUtil.actionSuccess("评论成功", comment);
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public Result listAllCourseAppoint() {
        List<CourseAppoint> data = new ArrayList<>();
        data.add(CourseAppoint.ONLINE);
        data.add(CourseAppoint.OFFLINE);
        return ResultUtil.actionSuccess("查询成功", data);
    }

    @Override
    public Result updateOrderForRefund(Integer userId, String orderId) {
        try {
            Order order = orderMapper.selectByPrimaryKey(orderId);
            if (userId != order.getPayerId()) {
                return ResultUtil.illegalOperation();
            }
            if (order.getOrderStatus() != OrderUtil.PAIED) {
                return ResultUtil.errorOperation("订单状态出错，请与管理员联系");
            }
            order.setOrderStatus(OrderUtil.APPLICATIONFORDRAWBACK);
            orderMapper.updateByPrimaryKeySelective(order);
            return ResultUtil.actionSuccess("已提交申请，等待客服处理中...", order);
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public Result updateOrderForCancel(Integer userId, String orderId) {
        try {
            Order order = orderMapper.selectByPrimaryKey(orderId);
            if (order.getOrderStatus() != OrderUtil.NEWORDER) {
                return ResultUtil.errorOperation("订单状态出错，请与管理员联系");
            }
            if (order.getPayerId() != userId) {
                return ResultUtil.errorOperation("非法操作");
            }
            orderMapper.updateStatusByOrderId(orderId, OrderUtil.CANCELED);
            return ResultUtil.actionSuccess("订单已取消", order);
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }

    }

    @Override
    public Result findCoachPhoneByUserId(Integer userId) {
        try {
            String phone = userMapper.selectPhoneByUserId(userId);
            return ResultUtil.actionSuccess("查找成功", phone);
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public Result repeatOrder(Integer userId, String orderId) {
       try {
           Order order = orderMapper.selectByPrimaryKey(orderId);
           if (order.getPayerId() != userId) {
               return ResultUtil.illegalOperation();
           }
           Order repeatOrder = OrderUtil.getRepeatOrder(order);
           orderMapper.insertSelective(repeatOrder);
           return ResultUtil.actionSuccess("已下单，等待瑜伽师处理中...", repeatOrder);
       }catch (SQLException e){
           e.printStackTrace();
           throw new RuntimeException();
       }
    }

    /*
     * @Author liufeng
     * @Description //生成一条钱包记录，处理学员为课程付款的订单的记录
     **/
    WalletRecord getStudentPayCourseRecord(Order order) {
        WalletRecord studentPayCourseRecord = new WalletRecord();
        studentPayCourseRecord.setFromId(order.getPayerId());
        studentPayCourseRecord.setToId(Attributes.PLATFORMNUMBER);//平台id
        studentPayCourseRecord.setMoney(order.getDiscount());
        studentPayCourseRecord.setPayType(0);
        studentPayCourseRecord.setRemark("课程付款，订单号为：" + order.getOrderId());
        return studentPayCourseRecord;
    }

    /*
     * @Author liufeng
     * @Description //生成一条钱包记录：学员付款后，平台为瑜伽师或所在平台付款
     **/
    WalletRecord getPlatfotmPayCourseRecord(Order order)throws SQLException {
        WalletRecord platfotmPayCourseRecord = new WalletRecord();
        platfotmPayCourseRecord.setFromId(Attributes.PLATFORMNUMBER);
        Integer acceptId = order.getAccepterId();
        Integer venueId = coachMapper.findVenueBycoachId(acceptId);
        if (venueId != null) {
            acceptId = coachMapper.findVenueByVenueId(venueId);
        }
        platfotmPayCourseRecord.setToId(acceptId);//瑜伽师或场馆
        platfotmPayCourseRecord.setMoney(order.getDiscount().subtract(new BigDecimal(Attributes.COMMISSION)));
        platfotmPayCourseRecord.setPayType(0);
        platfotmPayCourseRecord.setRemark("学员课程付款，转账给瑜伽师，订单号为：" + order.getOrderId());
        return platfotmPayCourseRecord;
    }

    /*
     * @Author liufeng
     * @Description //学员为课程付款，瑜伽师（或所在场馆）钱包余额增加，生成钱包记录
     **/
    WalletRecord getCoachCourseRecord(Order order) {
        WalletRecord coachCourseRecord = new WalletRecord();
        coachCourseRecord.setFromId(Attributes.PLATFORMNUMBER);
        coachCourseRecord.setToId(order.getAccepterId());
        coachCourseRecord.setMoney(order.getDiscount().subtract(new BigDecimal(Attributes.COMMISSION)));
        coachCourseRecord.setPayType(0);
        coachCourseRecord.setRemark("学员课程付款，订单号为：" + order.getOrderId());
        return coachCourseRecord;
    }


}
