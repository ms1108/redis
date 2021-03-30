package com.mosn;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import java.util.List;
//pipeline,其中有一个命令失败，其他的继续执行，非原子操作
public class PipelineTest {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1",6379);
        Pipeline pipelined = jedis.pipelined();
        for (int i = 0; i < 10; i++) {
            pipelined.incr("pipelineKey");
            pipelined.set("k"+i,"v"+i);
        }
        List<Object> list = pipelined.syncAndReturnAll();
        System.out.println(list);
    }
}
