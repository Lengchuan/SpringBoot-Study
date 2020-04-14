package com.lengchuan.springBoot.redis.service.impl;

import com.lengchuan.springBoot.redis.dao.UserDao;
import com.lengchuan.springBoot.redis.model.User;
import com.lengchuan.springBoot.redis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lengchuan <lishuijun1992@gmail.com>
 * @date 17-5-8
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;


	@Override
	public void createUser(User user) {
		userDao.insertUser(user);
	}

	@Override
	public User getUser(Integer id) {
		User user = userDao.getUser(id);
		return user;
	}
}
