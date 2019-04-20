package com.woniu.yoga.user.service.serviceImpl;

import com.woniu.yoga.user.dao.*;
import com.woniu.yoga.user.dto.SearchConditionDTO;
import com.woniu.yoga.user.pojo.*;
import com.woniu.yoga.user.service.StudentService;
import com.woniu.yoga.user.util.ConvertVOToDTOUtil;
import com.woniu.yoga.user.util.GetBmapDistanceUtil;
import com.woniu.yoga.user.vo.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author liufeng
 * @ClassName StudentService
 * @Date 2019/4/18 15:30
 * @Version 1.0
 * @Description
 **/
public class StudentServiceImpl implements StudentService {
//    @Autowired
//    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;
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
//    private CourseRepository courseRepository;
//    @Autowired
//    private CouponRepository couponRepository;
//
//    @Autowired
//    private OrderRepository orderRepository;
//    @Autowired
//    private CommentRepository commentRepository;

    //查找学员周边的教练、场馆
    @Override
    public List<UserVO> listAroundUserByIdOrAddress(SearchConditionVO searchConditionVO) {
        //判断用户是否开发定位给app，null则没有开启定位，需要提示用户开启定位
        if (searchConditionVO.getLongitude() == null || searchConditionVO.getLongitude() == null) {
            return null;
        }
        //判断角色id是否合法，2：教练；3：场馆；
        if (searchConditionVO.getRoleId() != 2 && searchConditionVO.getRoleId() != 3) {
            return null;
        }
        //double[4] 西侧经度，东侧经度，南侧纬度，北侧纬度
        double bounds[] = GetBmapDistanceUtil.getRange(searchConditionVO.getLongitude(), searchConditionVO.getLatitude(), searchConditionVO.getRound());
        SearchConditionDTO searchConditionDTO = ConvertVOToDTOUtil.searchConditionConvert(bounds, searchConditionVO);
        List<UserVO> userVOs = userMapper.listAroundUser(searchConditionDTO);
        return userVOs;
    }

    //查看瑜伽师、场馆的详细信息
    //mapper未书写
    //如果是教练，查找头像、姓名、简介、流派、认证方式（单击查看场馆）、课程（单击查看课程），交易次数，好评率
    //好友或者公开才能查看qq、微信、电话等信息
    //如果是场馆，查找头像，realName，简介，教练
    @Override
    public UserDetailInfoVo getDetailInfoByUserId(Integer userId) {
        UserDetailInfoVo userDetailInfoVo = new UserDetailInfoVo();

        return userMapper.getDetailInfoByUserId(userId);
    }

    //学员下单
    @Override
    public Result saveOrder(Order order) {
        Wallet wallet = walletMapper.findByUserId(order.getPayerId());
        Course course = courseMapper.selectByPrimaryKey(order.getCourseId());
        BigDecimal orderMoney = course.getCoursePrice().multiply(new BigDecimal(order.getCourseCount()));
        if (orderMoney.compareTo(BigDecimal.valueOf(wallet.getBalance())) < 0) {
            return new Result(1, "余额不足，请充值");
        }
        order.setOrderMoney(orderMoney);
        order.setOrderStatus(0);//0为新下单状态
        order.setOrderId("");//等待生成工具，生成n位订单号
        //插入订单
        int row = orderMapper.insertSelective(order);
        if (row == 1) {
            return new Result(0, "已下单，等待处理中...", order);
        } else {
            return new Result(1, "服务器连接错误，请稍后再试...");
        }
    }

    //学员付款前的订单金额更新，返回订单确认信息
    @Override
    public Result updateOrderWithCoupon(String orderId, Integer couponId) {
        int faceValue = 0;
        if (couponId != null) {
            Coupon coupon = couponMapper.selectByPrimaryKey(couponId);
            if (coupon == null) {
                return new Result(1, "优惠券不合法，请联系管理员");
            }
            faceValue = coupon.getFaceValue();
        }
        Order order = orderMapper.selectByPrimaryKey(orderId);
        User user = userMapper.selectByPrimaryKey(order.getPayerId());
        //查询会员等级，打折，计算应付款
        int discount =userMapper.selectDiscountByLevel(user.getUserLevel());
//        BigDecimal discount = null;
        return null;
    }

    @Override
    public Result updateOrderForPay(Integer orderId) {
        //学员钱包余额减少，添加钱包记录，更改订单、优惠券状态、
        return null;
    }

    @Override
    public Comment saveComment(Comment comment) {
        int row = commentMapper.insertSelective(comment);
        //会员等级，积分变化，交易记录，并返回评论详细信息
        //根据金额转换积分，修改积分
        //根据积分转换等级
        return null;
    }

    @Override
    public List<CourseAppoint> listAllCourseAppoint() {
        List<CourseAppoint> appoints = new ArrayList<>();
        appoints.add(CourseAppoint.ONLINE);
        appoints.add(CourseAppoint.OFFLINE);
        return appoints;
    }
}
