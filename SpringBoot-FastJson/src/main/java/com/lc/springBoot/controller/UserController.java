package com.lc.springBoot.controller;

import com.alibaba.fastjson.JSON;
import com.lc.springBoot.druid.model.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lsj <lishuijun1992@gmail.com>
 * @date 17-3-28
 */
@RestController
@RequestMapping("user")
public class UserController {

    @RequestMapping("/getUser")
    public User getUser() {
        User user = new User();
        user.setName("test");
        user.setEmail("test@123.com");
        user.setAge(25);
        return user;
    }

    @RequestMapping("/createUser")
    public String getcreateUserUser(@RequestBody User user) {
        System.out.println(JSON.toJSON(user));
        return "保存成功";
    }

}
