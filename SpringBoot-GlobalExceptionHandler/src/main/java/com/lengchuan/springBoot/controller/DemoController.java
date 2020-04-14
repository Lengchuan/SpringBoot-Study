package com.lengchuan.springBoot.controller;

import com.lengchuan.springBoot.Exception.MyException1;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lengchuan <lishuijun1992@gmail.com>
 * @date 17-3-27
 */
@RestController
@RequestMapping("/demo")
public class DemoController {

    @RequestMapping("/default")
    public String defaultException() {
        throw new NullPointerException();
    }

    @RequestMapping("/my")
    public String MyException() throws MyException1 {
        throw new MyException1();
    }
}
