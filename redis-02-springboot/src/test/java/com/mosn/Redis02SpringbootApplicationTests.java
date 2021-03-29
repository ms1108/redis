package com.mosn;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mosn.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class Redis02SpringbootApplicationTests {
	@Autowired
	public RedisTemplate redisTemplate;

	@Test
	void contextLoads() {
		//redisTemplate.opsForValue()操作字符串的
		//redisTemplate.getConnectionFactory().getConnection().flushAll();获取连接对象操作数据库
		redisTemplate.opsForValue().set("mykey","java");
		System.out.println(redisTemplate.opsForValue().get("mykey"));
	}
	@Test
	void test() throws JsonProcessingException {
		User user = new User("mosheng", 3);
		//序列化
		//String jsonUser = new ObjectMapper().writeValueAsString(user);
		redisTemplate.opsForValue().set("user",user);
		System.out.println(redisTemplate.opsForValue().get("user"));
	}

	//@Test
	//public void pipeline(){
	//	redisTemplate.executePipelined()
	//}
}
