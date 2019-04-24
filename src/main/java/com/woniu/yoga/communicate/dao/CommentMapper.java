package com.woniu.yoga.communicate.dao;

import com.woniu.yoga.communicate.pojo.Comment;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentMapper {
    int deleteByPrimaryKey(Long commentId);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Long commentId);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);
}