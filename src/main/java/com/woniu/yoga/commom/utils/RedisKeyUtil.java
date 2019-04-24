package com.woniu.yoga.commom.utils;

/**
 * @Description redis键值生成工具类
 * @Author Administrator
 * @Date 2019/4/24 19:12
 * @Version 1.0
 */
public class RedisKeyUtil {
    private static String SPLIT = ":";
    private static String BIZ_LIKE = "LIKE";
    private static String BIZ_DISLIKE = "DISLIKE";

    /**
     * @Description 生成点赞的key
     * @Author guochxi
     * @Date 19:19 2019/4/24
     * @Param [entityType, entityId]
     * @return java.lang.String
     **/
    public static String getLikeKey(int entityType,int entityId){
        return BIZ_LIKE + SPLIT + String.valueOf(entityType) + String.valueOf(entityId);
    }

    /**
     * @Description 生成点踩的key
     * @Author guochxi
     * @Date 19:20 2019/4/24
     * @Param [entityType, entityId]
     * @return java.lang.String
     **/
    public static String getDislikeKey(int entityType,int entityId){
        return BIZ_DISLIKE + SPLIT + String.valueOf(entityType) + String.valueOf(entityId);
    }
}
