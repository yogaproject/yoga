package com.woniu.yoga.communicate.service;

/**
 * @Description 点赞点踩
 * @Author guochxi
 * @Date 2019/4/24 19:00
 * @Version 1.0
 */
public interface LikeService {
    /**
     * @Description 某个用户对某个实体的点赞状态，由具体的实体调用并返回给前台
     * @Author guochxi
     * @Date 19:25 2019/4/24
     * @Param [userId, entityType, entityId]
     * @return int  1:已点赞，0：未操作，-1：已点踩
     **/
    int getLikeStatus(int userId,int entityType,int entityId);

    /**
     * @Description 获取某个实体的点赞数量，由具体的实体调用并返回给前台
     * @Author guochxi
     * @Date 19:36 2019/4/24
     * @Param [entityType, EntityId]
     * @return long
     **/
    long getLikeCount(int entityType,int entityId);

    /**
     * @Description 点赞
     * @Author guochxi
     * @Date 19:37 2019/4/24
     * @Param [userId, entityType, entityId]
     * @return long
     **/
    long like(int userId,int entityType,int entityId);

    /**
     * @Description 点踩
     * @Author guochxi
     * @Date 19:37 2019/4/24
     * @Param [userId, entityType, entityId]
     * @return long
     **/
    long dislike(int userId,int entityType,int entityId);
}
