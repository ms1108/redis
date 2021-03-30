package com.mosn;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.nio.charset.Charset;
//单机的bloom过滤器可以使用guava，分布式中redisson也实现了bloom过滤器
public class BloomFilterTest {
    //初始化布隆过滤器
    //1000:期望存入的数据个数，0.001：期望的错误率
    public BloomFilter<String> bloomFilter = BloomFilter.create(Funnels.stringFunnel(Charset.forName("utf-8")), 1000, 0.001);

    //把所有数据存入布隆过滤器
    public void init(String[] key) {
        for (String k : key) {
            bloomFilter.put(k);
        }
    }

    public String get(String key) {
        //从布隆过滤器这一级缓存中判断这个key存不存在
        boolean exist = bloomFilter.mightContain(key);
        if (!exist) {
            return "";
        }
        //操作缓存

        return "";
    }
}
