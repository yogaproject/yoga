package com.woniu.yoga.communicate.service.impl;

import com.github.pagehelper.PageInfo;
import com.woniu.yoga.commom.utils.CommentUtil;
import com.woniu.yoga.commom.vo.Result;
import com.woniu.yoga.communicate.dao.CommentMapper;
import com.woniu.yoga.communicate.pojo.Comment;
import com.woniu.yoga.communicate.service.CommentService;
import com.woniu.yoga.communicate.vo.CommentVo;
import com.woniu.yoga.user.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author huijie yan
 * @version 1.0.0
 * @description 评论业务层
 * @date 2019/4/23 15:10
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    /**
    * @Description 展示评论
    * @param mid
    * @param currentPage
    * @param pageSize
    * @author huijie yan
    * @date 2019/4/23
    * @return com.woniu.yoga.commom.vo.Result
    */
    @Override
    public Result showComments(Integer mid, Integer currentPage, Integer pageSize) {
        if (mid == 0){
            return Result.error("未获取到动态内容id:m_id");
        }
        List<CommentVo> list = commentMapper.queryComments(mid, currentPage, pageSize);
        PageInfo pageInfo = new PageInfo(list);
        for (CommentVo vo:list) {
            String publishTime = CommentUtil.publishTime(vo.getCommentCreateTime());
            vo.setPublishTime(publishTime);
        }

        return Result.success("成功",list);

    }

    /**
    * @Description 添加评论
    * @param comment
    * @author huijie yan
    * @date 2019/4/23
    * @return com.woniu.yoga.commom.vo.Result
    */
    @Override
    public Result addComment(Comment comment, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user.getUserPhone() == null){
            return Result.error("未绑定手机不能评论");
        }
        commentMapper.insertSelective(comment);
        return Result.success("添加成功");
    }

    /**
    * @Description 删除评论
    * @param commentId
    * @author huijie yan
    * @date 2019/4/26
    * @return com.woniu.yoga.commom.vo.Result
    */
    @Override
    public Result deleteComment(Integer commentId) {
        commentMapper.deleteByPrimaryKey(commentId);
        return Result.success("删除成功");
    }
}
