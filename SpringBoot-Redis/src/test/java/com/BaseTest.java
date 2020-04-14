package com;

import com.lengchuan.springBoot.redis.model.User;
import com.lengchuan.springBoot.redis.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author lengchuan <lishuijun1992@gmail.com>
 * @date 17-4-5
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class)
public class BaseTest {

    private final static Logger logger = LoggerFactory.getLogger(BaseTest.class);

    @Autowired
    private UserService userService;

    @Test
    public void add() {

        User u = new User();
        u.setId(1);
        u.setName("lengchuan");
        u.setEmail("lishuijun1992@gmail.com");

        userService.createUser(u);

    }

    @Test
    public void get() {

        logger.info("获取到用户信息：{}", userService.getUser(1));

    }
}
