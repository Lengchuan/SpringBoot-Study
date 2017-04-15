package com.lc.springBoot.jpa.config.druid;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * write数据源配置
 *
 * @author lsj <lishuijun1992@gmail.com>
 * @date 17-4-13
 */
@Configuration
public class WriteDruidDataSourceConfig {

    /**
     * DataSource 配置
     * @return
     */
    @ConfigurationProperties(prefix = "spring.datasource.druid.write")
    @Bean(name = "writeDruidDataSource")
    public DataSource writeDruidDataSource() {
        return new DruidDataSource();
    }

    /**
     * EntityManager类似于jdbc的Connection,Hibernate的Session,mybatis的sqlSession
     * 总之,在执行操作之前,我们总要获取这个对象的一个实例.
     * @return
     */
}
