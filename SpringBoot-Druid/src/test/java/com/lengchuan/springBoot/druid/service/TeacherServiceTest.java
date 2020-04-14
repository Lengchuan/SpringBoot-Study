package com.lengchuan.springBoot.druid.service;

import com.BaseTest;
import com.lengchuan.springBoot.druid.model.Teacher;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author lengchuan <lishuijun1992@gmail.com>
 * @date 17-4-5
 */
public class TeacherServiceTest extends BaseTest{

    @Autowired
    private TeacherService teacherService;

    @Test
    public void createTeacher() throws Exception {
        Teacher teacher = new Teacher();
        teacher.setName("test");
        teacherService.createTeacher(teacher);
    }

}