package com.lc.springBoot.druid.mapper.master;

import com.lc.springBoot.druid.model.Student;

import java.util.List;

/**
 * @author lsj <lishuijun1992@gmail.com>
 * @date 17-4-4
 */
public interface StudentMapper {

    int insert(Student student);

    List<Student> getBypage();
}
