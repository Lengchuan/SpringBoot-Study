package com.lc.springBoot.druid.service;

import com.lc.springBoot.druid.mapper.cluster1.TeacherMapper;
import com.lc.springBoot.druid.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author lsj <lishuijun1992@gmail.com>
 * @date 17-4-4
 */
@Service
public class TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    @Transactional
    public boolean createTeacher(Teacher teacher) {
        teacherMapper.insert(teacher);
        return true;
    }
}
