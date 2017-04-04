package com.lc.springBoot.druid.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author lsj <lishuijun1992@gmail.com>
 * @date 17-4-4
 */
public class User implements Serializable {
    private Integer userId;

    private String name;

    private String email;

    private Integer age;

    private Date birthday;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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
