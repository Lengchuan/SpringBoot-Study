package com.lengchuan.springBoot.redis.dao;

import com.lengchuan.springBoot.redis.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

/**
 * @author lengchuan <lishuijun1992@gmail.com>
 * @date 17-5-8
 */
@Repository
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

		User user = (User) valueOperations.get(USER_PREFIX + id);
		return user;
	}
}
