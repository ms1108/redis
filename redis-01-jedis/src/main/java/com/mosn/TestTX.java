package com.mosn;

import com.alibaba.fastjson.JSONObject;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

public class TestTX {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.flushAll();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("hello", "word");
        jsonObject.put("name", "mosn");
        String result = jsonObject.toJSONString();
        //开启事务
        Transaction multi = jedis.multi();
        //jedis.watch(key);加乐观锁监控
        try {
            multi.set("user1", result);
            multi.set("user2", result);
            int i = 1 / 0;//抛出异常
            multi.exec();//执行事务
        } catch (Exception e) {
            multi.discard();//放弃事务
            e.printStackTrace();
        } finally {
            System.out.println(jedis.get("user1"));
            System.out.println(jedis.get("user2"));
            jedis.close();
        }


    }
}
