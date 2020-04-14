package com.lengchuan.springBoot.druid.service;

import com.App;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author lengchuan <lishuijun1992@gmail.com>
 * @date 17-4-3
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class)
public class HelloWorldServiceTest {

    @Autowired
    private HelloWorldService helloWorldService;

    @Test
    public void sayHello() throws Exception {
        helloWorldService.sayHello();
    }

}