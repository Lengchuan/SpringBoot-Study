package com.lc.springBoot.jpa.config.dataSource;

import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * jpa相关属性设置,其实就是对hiberntae的一些相关属性设置,
 * 当然,我们如果按照springboot的规范去定义这些属性,我们就不用自己写这个类了
 * @author lsj <lishuijun1992@gmail.com>
 * @date 17-4-15
 */
@Configuration
public class MyJpaProperties {

    @ConfigurationProperties(prefix = "spring.jpa")
    @Bean
    @Primary
    public JpaProperties jpaProperties() {
        return new JpaProperties();
    }

}
