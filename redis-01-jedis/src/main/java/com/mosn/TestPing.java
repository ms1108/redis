package com.mosn;

import redis.clients.jedis.Jedis;

import java.util.HashSet;
import java.util.Set;

public class TestPing {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1",6379);
        System.out.println(jedis.ping());
    }
}
