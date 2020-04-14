package com;

import com.lengchuan.springBoot.converter.EncrypConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;

/**
 * @author lengchuan <lishuijun1992@gmail.com>
 * @date 17-3-28
 */
@SpringBootApplication
public class App {

//    @Bean
//    HttpMessageConverters setFastJsonConverter() {
//        return new HttpMessageConverters(new FastJsonConverter());
//    }

    @Bean
    HttpMessageConverters fastJsonHttpMessageConverters() {
        return new HttpMessageConverters(new EncrypConverter());
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
