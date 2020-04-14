package com.lengchuan.springBoot.druid.service;

import com.lengchuan.springBoot.druid.mapper.cluster.ClassMapper;
import com.lengchuan.springBoot.druid.model.Class;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author lengchuan <lishuijun1992@gmail.com>
 * @date 17-4-4
 */
@Service
public class ClassService {

    @Autowired
    private ClassMapper classMapper;

    @Transactional
    public boolean createClass(Class c) {
        classMapper.insert(c);
        return true;
    }
}
