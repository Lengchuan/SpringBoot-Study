package com.lc.springBoot;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 返回json
 *
 * @author lsj <lishuijun1992@gmail.com>
 * @date 17-3-27
 */
@RestController
@RequestMapping("/demo")
public class DemoController {

    @RequestMapping("/getUser")
    public User getUser() {
        User user = new User();
        user.setName("lengchuan");
        user.setAge(25);
        user.setCity("Beijing");

        return user;
    }

}

class User {

    private String name;
    private int age;
    private String city;

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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
