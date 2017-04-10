package com.lc.springBoot.druid.controller;

import com.lc.springBoot.druid.model.Student;
import com.lc.springBoot.druid.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author lsj <lishuijun1992@gmail.com>
 * @date 17-4-8
 */
@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping("/getStudents")
    public List<Student> getStudents(Integer page, Integer rows) {

        return studentService.getByPage(page, rows);
    }
}
