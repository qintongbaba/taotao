package com.wuqinghua.taotao.common.utils;

import redis.clients.jedis.Jedis;

/**
 * Created by wuqinghua on 17/11/11.
 */
public class JedisManagerUtils {

    private static JedisUtils jedisUtils = SpringContentHolder.getBean(JedisUtils.class);


    public static final String PREFIX = "taotao:biz";


    public static void set(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = jedisUtils.getJedis();
            if (jedis != null) {
                jedis.set(key.getBytes(), value.getBytes());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
    }


    public static byte[] get(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisUtils.getJedis();
            if (jedis != null) {
                byte[] bytes = jedis.get(key.getBytes());
                return bytes;
            }
        } catch (Exception e) {
            jedis.close();
        }
        return null;
    }

}
