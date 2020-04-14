package com.lengchuan.springBoot.controller2;

import com.lengchuan.springBoot.Exception.MyException1;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lengchuan <lishuijun1992@gmail.com>
 * @date 17-3-27
 */
@RestController
@RequestMapping("/demo2")
public class DemoController2 {

    @RequestMapping("/default")
    public String defaultException() {
        throw new NullPointerException();
    }

    @RequestMapping("/my")
    public String MyException() throws MyException1 {
        throw new MyException1();
    }
}
