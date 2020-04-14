package com.lengchuan.springBoot.druid.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lengchuan.springBoot.druid.ReadAndWrite.annotation.TargetDataSource;
import com.lengchuan.springBoot.druid.mapper.master.StudentMapper;
import com.lengchuan.springBoot.druid.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author lengchuan <lishuijun1992@gmail.com>
 * @date 17-4-4
 */
@Service
public class StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Transactional
    @TargetDataSource(dataSource = "writeDataSource")
    public boolean createUser(Student student) {
        studentMapper.insert(student);

        //事务测试
//        int i = 1 / 0;
        return true;
    }

    @TargetDataSource(dataSource = "read1DataSource")
    public List<Student> getByPage(int page, int rows) {
        Page<Student> studentPage = PageHelper.startPage(page, rows, true);
        List<Student> students = studentMapper.getBypage();
        System.out.println("-------------------" + studentPage.toString() + "-----------");
        return students;
    }
}
