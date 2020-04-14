package com.lengchuan.springBoot.druid.config;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置mybatis需要扫描的mapper接口
 *
 * @author lengchuan <lishuijun1992@gmail.com>
 * @date 17-4-4
 */
@Configuration
//MapperScannerConfigurer执行的比较早，所以必须有下面的注解
@AutoConfigureAfter(MybatisConfig.class)
public class MapperScannerConfig {

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com.lengchuan.springBoot.dataSource.mapper");
        return mapperScannerConfigurer;
    }
}
