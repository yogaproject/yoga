package com.woniu.yoga.commom.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @Description redis工具类
 * @Author Administrator
 * @Date 2019/4/24 15:01
 * @Version 1.0
 */
@Component
@Slf4j
public class RedisUtil{
    @Autowired
    private JedisPool jedisPool;

    /**
     * @Description 向集合中添加元素
     * @Author guochxi
     * @Date 17:58 2019/4/24
     * @Param [key, value]
     * @return long
     **/
    public long sadd(String key,String value){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.sadd(key,value);
        } catch (Exception e){
            log.error("【jedisPool异常】+{}",e.getMessage());
            if(jedis != null){
                jedis.close();
            }
        } finally {
            if(jedis != null){
                jedis.close();
            }
        }
        return 0;
    }

    /**
     * @Description 移除集合中的元素
     * @Author guochxi
     * @Date 17:57 2019/4/24
     * @Param [key, value]
     * @return long
     **/
    public long srem(String key,String value){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.srem(key,value);
        } catch (Exception e){
            log.error("【jedisPool异常】+{}",e.getMessage());
            if(jedis != null){
                jedis.close();
            }
        } finally {
            if(jedis != null){
                jedis.close();
            }
        }
        return 0;
    }

    /**
     * @Description 集合中元素的数量
     * @Author guochxi
     * @Date 17:58 2019/4/24
     * @Param [key]
     * @return long
     **/
    public long scard(String key){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.scard(key);
        } catch (Exception e){
            log.error("【jedisPool异常】+{}",e.getMessage());
            if(jedis != null){
                jedis.close();
            }
        } finally {
            if(jedis != null){
                jedis.close();
            }
        }
        return 0;
    }
    
    /**
     * @Description 判断value元素是否是集合key的成员
     * @Author guochxi
     * @Date 18:03 2019/4/24
     * @Param [key]
     * @return long
     **/
    public boolean sisMember(String key,String value){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.sismember(key, value);
        } catch (Exception e){
            log.error("【jedisPool异常】+{}",e.getMessage());
            if(jedis != null){
                jedis.close();
            }
        } finally {
            if(jedis != null){
                jedis.close();
            }
        }
        return false;
    }

}
