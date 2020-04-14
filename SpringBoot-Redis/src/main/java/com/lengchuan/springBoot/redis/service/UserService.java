package com.lengchuan.springBoot.redis.service;

import com.lengchuan.springBoot.redis.model.User;

/**
 * @author lengchuan <lishuijun1992@gmail.com>
 * @date 17-5-8
 */
public interface UserService {

    void createUser(User user);

	User getUser(Integer id);
}
