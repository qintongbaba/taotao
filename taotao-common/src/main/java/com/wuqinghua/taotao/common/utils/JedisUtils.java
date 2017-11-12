package com.wuqinghua.taotao.common.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import java.io.IOException;

/**
 * Created by wuqinghua on 17/11/11.
 */
@Component
public class JedisUtils {


    //单机
    @Autowired
    public JedisPool jedisPool;


    //集群
    @Autowired
    public JedisCluster jedisCluster;


    public Jedis getJedis() {
        Jedis resource = jedisPool.getResource();
        return resource;
    }


    public void closeJedis(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }


    public JedisCluster getJedisCluster() {
        return jedisCluster;
    }

    public void closeJedisCluster() {
        try {
            jedisCluster.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
