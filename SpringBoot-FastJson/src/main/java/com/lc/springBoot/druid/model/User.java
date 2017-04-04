package com.lc.springBoot.druid.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lsj <lishuijun1992@gmail.com>
 * @date 17-3-28
 */
public class User implements Serializable{
//    @JSONField(serialize = false)
    private String name;
    private int age;
    private String email;
    private List<User> friends;

    public User(String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public User() {
        friends = new ArrayList<User>();
        friends.add(new User("lc1", 25, "123@123.test"));
        friends.add(new User("lc2", 25, "1234@123.test"));
        friends.add(new User("lc3", 25, "12345@123.test"));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<User> getFriends() {
        return friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }
}
