package com.lc.springBoot.service;

import com.App;
import com.lc.springBoot.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Date;

/**
 * @author lsj <lishuijun1992@gmail.com>
 * @date 17-4-4
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class)
@EnableTransactionManagement
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

}