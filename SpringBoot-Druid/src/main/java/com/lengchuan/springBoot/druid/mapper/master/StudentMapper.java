package com.lengchuan.springBoot.druid.mapper.master;

import com.lengchuan.springBoot.druid.model.Student;

import java.util.List;

/**
 * @author lengchuan <lishuijun1992@gmail.com>
 * @date 17-4-4
 */
public interface StudentMapper {

    int insert(Student student);

    List<Student> getBypage();
}
