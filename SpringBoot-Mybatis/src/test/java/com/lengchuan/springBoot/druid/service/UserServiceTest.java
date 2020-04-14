package com.lengchuan.springBoot.druid.service;

import com.App;
import com.lengchuan.springBoot.druid.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * @author lengchuan <lishuijun1992@gmail.com>
 * @date 17-4-4
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class)
public class UserServiceTest {


    @Autowired
    private UserService userService;

    @Test
    public void createUser() throws Exception {
        User user = new User();
        user.setName("test1");
        user.setAge(1);
        user.setBirthday(new Date());
        user.setEmail("test@test.com");
        userService.createUser(user);
    }

    @Test
    public void getByPage() throws Exception {
        userService.getByPage(1, 2);
    }

}