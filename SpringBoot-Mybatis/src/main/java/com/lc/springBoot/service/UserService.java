package com.lc.springBoot.service;

import com.lc.springBoot.mapper.UserMapper;
import com.lc.springBoot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author lsj <lishuijun1992@gmail.com>
 * @date 17-4-4
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Transactional
    public boolean createUser(User user) {
        userMapper.insert(user);

        //事物测试
        int i = 1 / 0;
        return true;
    }
}
