package com.lengchuan.springBoot.druid.mapper;

import com.lengchuan.springBoot.druid.model.User;

import java.util.List;

/**
 * @author lengchuan <lishuijun1992@gmail.com>
 * @date 17-4-4
 */
public interface UserMapper {

    int insert(User user);

//    int update(User user);

//    int delete(User user);

//    User getById(int userId);

    List<User> getBypage();
}
