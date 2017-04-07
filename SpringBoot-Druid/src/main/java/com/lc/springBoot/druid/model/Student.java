package com.lc.springBoot.druid.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author lsj <lishuijun1992@gmail.com>
 * @date 17-4-4
 */
public class Student implements Serializable {
    private Integer id;

    private String name;

    private String email;

    private Integer age;

    private Date birthday;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
