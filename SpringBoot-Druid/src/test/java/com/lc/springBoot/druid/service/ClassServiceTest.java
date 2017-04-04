package com.lc.springBoot.druid.service;

import com.App;
import com.lc.springBoot.druid.model.Class;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author lsj <lishuijun1992@gmail.com>
 * @date 17-4-4
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class)
public class ClassServiceTest {

    @Autowired
    private ClassService classService;

    @Test
    public void createClass() throws Exception {
        Class c = new Class();
        c.setClassName("test");
        classService.createClass(c);
    }

}