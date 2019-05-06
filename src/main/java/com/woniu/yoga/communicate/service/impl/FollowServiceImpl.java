package com.woniu.yoga.communicate.service.impl;

import com.github.pagehelper.PageInfo;
import com.woniu.yoga.commom.utils.CommentUtil;
import com.woniu.yoga.communicate.constant.SysConstant;
import com.woniu.yoga.communicate.dao.FollowMapper;
import com.woniu.yoga.communicate.pojo.Follow;
import com.woniu.yoga.communicate.service.FollowService;
import com.woniu.yoga.communicate.vo.FollowVo;
import com.woniu.yoga.communicate.vo.MyVo;
import com.woniu.yoga.home.vo.HomepageVo;
import com.woniu.yoga.home.vo.Result;
import com.woniu.yoga.user.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @author huijie yan
 * @version 1.0.0
 * @description 我的关注业务层
 * @date 2019/4/24 14:36
 */
@Service
public class FollowServiceImpl implements FollowService {

    @Autowired
    private FollowMapper followMapper;


    @Override
    public Result showFollowList(Integer state, Integer userId) {
        List<FollowVo> list = new ArrayList<>();
        if (state == 1){
           list  = followMapper.queryFollowList(userId);
        }else if (state == 0){
            list = followMapper.queryFans(userId);
        } else {
            return Result.error("参数错误");
        }
        return Result.success("成功",list);
    }

    @Override
    public Result showFollowHomepage(HttpSession session) {
        User user = (User) session.getAttribute(SysConstant.CURRENT_USER);
        List<HomepageVo> list = followMapper.queryFollowHomepages(1);
        for (int i = 0; i < list.size(); i++){
            HomepageVo vo = list.get(i);
            //用户设置权限为好友，如果不是相互关注，不能查看
            if (vo.getUserPrivacy() == 1 && vo.getFollowStatus() == 0){
                list.remove(i);
                i--;
                continue;
            }
            //发布的动态设置权限为好友可见，如果不是相互关注，不能查看
            if (vo.getHomepagePrivacy() == 1 && vo.getFollowStatus() == 0){
                list.remove(i);
                i--;
                continue;
            }
            String publishTime = CommentUtil.publishTime(vo.getCreateTime());
            list.get(i).setPublishTime(publishTime);
        }

        return Result.success("成功",list);
    }

    @Override
    @Transactional
    public Result addFollow(Integer userId, HttpSession session) {
        User user = (User) session.getAttribute(SysConstant.CURRENT_USER);
        Integer uid = 1;
        //先查看他们是不是互相关注
        int count = followMapper.selectIfFollow(userId, uid);
        Follow follow = new Follow();
        follow.setUserId(uid);
        follow.setFollowedId(userId);
        //查找到一行数据，说明用户要关注的对象也关注了他
        if (count == 1){
            follow.setFollowStatus(1);
            //让用户关注的对象的状态该为1（互相关注状态）
            followMapper.updateFollowStatus(userId, uid);
            followMapper.insertSelective(follow);
        }else {
            follow.setFollowStatus(0);
            followMapper.insertSelective(follow);
        }
        return Result.success("关注成功");
    }

    @Override
    @Transactional
    public Result cancelFollow(Integer userId, HttpSession session) {
        User user = (User) session.getAttribute(SysConstant.CURRENT_USER);
        int followStatus = followMapper.selectFollowStatus(user.getUserId(), userId);
        //状态为0代表两个用户不是好友，取关只需要删除这个关注。为1需要修改被关注人的followStatus为0
        if (followStatus == 1){
            followMapper.updateCancelFollowStatus(userId, user.getUserId());
        }
        followMapper.deleteByFlag(user.getUserId(), userId);
        return Result.success("取关成功");
    }

    @Override
    public Result searchFollow(String userNickName, Integer userId) {
        List<FollowVo> list = followMapper.queryFollowUser(userId, userNickName);
        if (list.size() == 0){
            return Result.error("未搜索到相关用户");
        }
        return Result.success("成功",list);
    }

    @Override
    public Result showMyself(Integer userId) {
        Integer fansCount = followMapper.selectFansCount(userId);
        Integer followCount = followMapper.selectFollowCount(userId);
        MyVo myVo = followMapper.selectMyVo(userId);
        Integer homepageCount = followMapper.selectHomepageCount(userId);

        myVo.setFansCount(fansCount);
        myVo.setFollowCount(followCount);
        myVo.setHomepageCount(homepageCount);
        return Result.success("成功", myVo);
    }
}
