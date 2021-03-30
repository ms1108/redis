package com.mosn.config;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisLock {
    public Redisson redisson() {
        Config config = new Config();
        //单机
        config.useSingleServer().setAddress("redis://192.168.0.60:6379").setDatabase(0);

        /*集群
            config.useClusterServers()
                        .addNodeAddress("redis://192.168.0.60:6379")
                        .addNodeAddress("redis://192.168.0.60:6380");
         */

        return (Redisson) Redisson.create(config);
    }
    //使用方式
    @Autowired
    private Redisson redisson;
    public void method(){
        RLock redissonLock = redisson.getLock("lock");
        try {
            redissonLock.lock();
            //业务代码
        }finally {
            //解锁
            redissonLock.unlock();
        }
    }
}
