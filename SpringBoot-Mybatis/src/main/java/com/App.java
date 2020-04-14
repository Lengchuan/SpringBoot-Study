package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author lengchuan <lishuijun1992@gmail.com>
 * @date 17-4-4
 */
@SpringBootApplication
//@MapperScan(value = "com.lengchuan.springBoot.dataSource.mapper")//需要扫描的mapper接口所在包
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
