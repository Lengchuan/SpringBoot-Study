package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author lsj <lishuijun1992@gmail.com>
 * @date 17-4-4
 */
@SpringBootApplication
@MapperScan(value = "com.lc.springBoot.mapper")//需要扫描的mapper接口所在包
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
