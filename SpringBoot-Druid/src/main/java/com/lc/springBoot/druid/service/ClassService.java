package com.lc.springBoot.druid.service;

import com.lc.springBoot.druid.mapper.cluster.ClassMapper;
import com.lc.springBoot.druid.model.Class;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lsj <lishuijun1992@gmail.com>
 * @date 17-4-4
 */
@Service
public class ClassService {

    @Autowired
    private ClassMapper classMapper;

    public boolean createClass(Class c) {
        classMapper.insert(c);
        return true;
    }
}
