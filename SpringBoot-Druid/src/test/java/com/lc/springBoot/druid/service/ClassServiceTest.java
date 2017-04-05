package com.lc.springBoot.druid.service;

import com.BaseTest;
import com.lc.springBoot.druid.model.Class;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author lsj <lishuijun1992@gmail.com>
 * @date 17-4-4
 */
public class ClassServiceTest extends BaseTest{

    @Autowired
    private ClassService classService;

    @Test
    public void createClass() throws Exception {
        Class c = new Class();
        c.setClassName("test");
        classService.createClass(c);
    }

}