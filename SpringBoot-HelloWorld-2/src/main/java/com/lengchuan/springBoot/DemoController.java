package com.lengchuan.springBoot;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 返回json
 *
 * @author lengchuan <lishuijun1992@gmail.com>
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

