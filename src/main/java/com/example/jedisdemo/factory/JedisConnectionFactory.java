package com.example.jedisdemo.factory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisConnectionFactory {
    private static final JedisPool jedisPool;

    static {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(8);
        poolConfig.setMaxIdle(8);
        poolConfig.setMaxIdle(0);
        poolConfig.setMaxWaitMillis(1000);
        jedisPool = new JedisPool(poolConfig, "192.168.56.101", 6379, 1000);
    }

    public static Jedis getJedis(){
        return jedisPool.getResource();
    }

}
