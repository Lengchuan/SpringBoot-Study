package com;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lsj <lishuijun1992@gmail.com>
 * @date 17-3-26
 */
@SpringBootApplication
@RestController
public class HelloWorldApp2 {

    @RequestMapping("/hello")
    public String hello() {
        return "hello world";
    }

    @RequestMapping("/")
    public String index() {
        return "this is index page";
    }

    /**
     * 用于启动项目
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(HelloWorldApp2.class, args);
    }

}
