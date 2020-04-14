package com.lengchuan.springBoot.druid.service;

import com.BaseTest;
import com.lengchuan.springBoot.druid.model.Student;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * @author lengchuan <lishuijun1992@gmail.com>
 * @date 17-4-4
 */
public class StudentServiceTest extends BaseTest{


    @Autowired
    private StudentService studentService;

    @Test
    public void createUser() throws Exception {
        Student student = new Student();
        student.setName("test1");
        student.setAge(1);
        student.setBirthday(new Date());
        student.setEmail("test@test.com");
        studentService.createUser(student);
    }

    @Test
    public void getByPage() throws Exception {
        studentService.getByPage(1, 2);
    }

}