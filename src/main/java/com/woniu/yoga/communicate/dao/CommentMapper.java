package com.woniu.yoga.communicate.dao;

import com.woniu.yoga.communicate.pojo.Comment;
import com.woniu.yoga.communicate.vo.CommentVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CommentMapper {
    int deleteByPrimaryKey(Long commentId);

    int insert(Comment record);

    //添加评论
    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Long commentId);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);

    //查询动态内容下的评论
    List<CommentVo> queryComments(@Param("mid") Integer mid, @Param("currentPage") Integer currentPage, @Param("pageSize") Integer pageSize);
}