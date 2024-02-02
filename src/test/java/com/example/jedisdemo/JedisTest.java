package com.example.jedisdemo;

import com.example.jedisdemo.factory.JedisConnectionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

import java.util.Map;

public class JedisTest {
    private Jedis jedis;

    @BeforeEach
    void setUp() {
//        jedis = new Jedis("192.168.56.101", 6379);
        jedis = JedisConnectionFactory.getJedis();  //從連線池

//        jedis.auth("root");

        //選擇庫
        jedis.select(0);
    }

    @Test
    void testHash() {
        //插入Hash數據
        jedis.hset("user:1", "name", "Jack");
        jedis.hset("user:1", "age", "21");

        //獲取
        Map<String, String> map = jedis.hgetAll("user:1");
        System.out.println(map);
    }

    @Test
    void testString() {
        String result = jedis.set("name", "kurt");
        System.out.println("result = " + result);
        String name = jedis.get("name");
        System.out.println("name = " + name);
    }

    @AfterEach
    void testDown() {
        if (jedis != null) {
            jedis.close();
        }
    }
}
