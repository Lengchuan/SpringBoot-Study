package com.lc.springBoot.druid.model;

import java.io.Serializable;

/**
 * @author lsj <lishuijun1992@gmail.com>
 * @date 17-4-5
 */
public class Teacher implements Serializable {

    private Integer id;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
