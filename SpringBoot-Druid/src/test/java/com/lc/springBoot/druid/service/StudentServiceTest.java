package com.lc.springBoot.druid.service;

import com.App;
import com.lc.springBoot.druid.model.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * @author lsj <lishuijun1992@gmail.com>
 * @date 17-4-4
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class)
public class StudentServiceTest {


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