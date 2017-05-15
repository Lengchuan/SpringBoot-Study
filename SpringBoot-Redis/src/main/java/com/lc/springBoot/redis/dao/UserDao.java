package com.lc.springBoot.redis.dao;

import com.alibaba.fastjson.JSON;
import com.lc.springBoot.redis.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

/**
 * @author lengchuan <lishuijun1992@gmail.com>
 * @date 17-5-8
 */
@Component
public class UserDao {

	private final static String USER_PREFIX = "User:";
	@Autowired
	private RedisTemplate redisTemplate;

	public void insertUser(User user) {
		ValueOperations valueOperations = redisTemplate.opsForValue();
		valueOperations.set(USER_PREFIX + user.getId(), user);
	}


	public User getUser(Integer id) {
		ValueOperations valueOperations = redisTemplate.opsForValue();

		System.out.println(JSON.toJSON(valueOperations.get(USER_PREFIX + id)));
		User user = (User) valueOperations.get(USER_PREFIX + id);
		return user;
	}
}
