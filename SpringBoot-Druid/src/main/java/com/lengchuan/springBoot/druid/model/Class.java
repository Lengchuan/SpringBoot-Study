package com.lengchuan.springBoot.druid.model;

import java.io.Serializable;

/**
 * @author lengchuan <lishuijun1992@gmail.com>
 * @date 17-4-4
 */
public class Class implements Serializable {

    private int id;

    private String className;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
