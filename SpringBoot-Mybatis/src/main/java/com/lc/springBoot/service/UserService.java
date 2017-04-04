package com.lc.springBoot.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lc.springBoot.mapper.UserMapper;
import com.lc.springBoot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

        //事务测试
//        int i = 1 / 0;
        return true;
    }

    public List<User> getByPage(int page, int rows) {
        Page<User> userPage = PageHelper.startPage(page, rows, true);
        List<User> users = userMapper.getBypage();
        System.out.println("-------------------" + userPage.toString() + "-----------");
        return users;
    }
}
