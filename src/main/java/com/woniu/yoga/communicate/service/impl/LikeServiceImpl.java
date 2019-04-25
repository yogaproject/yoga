package com.woniu.yoga.communicate.service.impl;

import com.woniu.yoga.commom.utils.RedisKeyUtil;
import com.woniu.yoga.commom.utils.RedisUtil;
import com.woniu.yoga.communicate.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 点赞业务逻辑实现类
 * @Author Administrator
 * @Date 2019/4/24 19:03
 * @Version 1.0
 */
@Service
public class LikeServiceImpl implements LikeService {

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public long getLikeCount(int entityType, int entityId) {
        String likeKey = RedisKeyUtil.getLikeKey(entityType, entityId);
        return redisUtil.scard(likeKey);
    }

    @Override
    public int getLikeStatus(int userId, int entityType, int entityId) {
        String likeKey = RedisKeyUtil.getLikeKey(entityType, entityId);
        if(redisUtil.sisMember(likeKey,String.valueOf(userId))){
            return 1;
        }
        String dislikeKey = RedisKeyUtil.getDislikeKey(entityType,entityId);
        return redisUtil.sisMember(dislikeKey,String.valueOf(userId)) ? -1 : 0;
    }

    @Override
    public long like(int userId, int entityType, int entityId) {
        String likeKey = RedisKeyUtil.getLikeKey(entityType, entityId);
        String dislikeKey = RedisKeyUtil.getDislikeKey(entityType,entityId);
        redisUtil.sadd(likeKey,String.valueOf(userId));
        redisUtil.srem(dislikeKey, String.valueOf(userId));
        return redisUtil.scard(likeKey);
    }

    @Override
    public long dislike(int userId, int entityType, int entityId) {
        String dislikeKey = RedisKeyUtil.getDislikeKey(entityType,entityId);
        String likeKey = RedisKeyUtil.getLikeKey(entityType, entityId);
        redisUtil.sadd(dislikeKey,String.valueOf(userId));
        redisUtil.srem(likeKey, String.valueOf(userId));
        return redisUtil.scard(dislikeKey);
    }
}
