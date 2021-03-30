package com.mosn;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.Set;

//通过sentinel连接主节点，可以不用知道主节点的ip了，当主节点更改，也不用修改java代码了
public class TestSentinel {
    public static void main(String[] args) {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(20);
        config.setMaxIdle(10);
        config.setMinIdle(5);

        String masterName = "mymaster";
        Set<String> sentinel = new HashSet<>();
        sentinel.add(new HostAndPort("192.168.0.2", 26379).toString());
        sentinel.add(new HostAndPort("192.168.0.2", 26380).toString());
        sentinel.add(new HostAndPort("192.168.0.2", 26381).toString());
        JedisSentinelPool sentinelPool = new JedisSentinelPool(masterName, sentinel, config, 3000, null);
        Jedis jedis = null;

        try {
            jedis = sentinelPool.getResource();
            System.out.println(jedis.set("k1", "v1"));
            System.out.println(jedis.get("k1"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null)
                jedis.close();
        }
    }
}
