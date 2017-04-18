package com.lc.springBoot.jpa.config.ReadAndWrite.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lsj <lishuijun1992@gmail.com>
 * @date 17 -4-5
 */
@Configuration
public class DataSourceConfig {

    private final static String WRITE_DATASOURCE_KEY = "writeDruidDataSource";
    private final static String READ_DATASOURCE_KEY = "readDruidDataSource";

    /**
     * 注入AbstractRoutingDataSource
     * @param readDruidDataSource
     * @param writeDruidDataSource
     * @return
     * @throws Exception
     */
    @Bean
    public AbstractRoutingDataSource routingDataSource(
            @Qualifier(READ_DATASOURCE_KEY) DataSource readDruidDataSource,
            @Qualifier(WRITE_DATASOURCE_KEY) DataSource writeDruidDataSource
    ) throws Exception {
        DynamicDataSource dataSource = new DynamicDataSource();

        Map<Object, Object> targetDataSources = new HashMap();
        targetDataSources.put(WRITE_DATASOURCE_KEY, writeDruidDataSource);
        targetDataSources.put(READ_DATASOURCE_KEY, readDruidDataSource);
        dataSource.setTargetDataSources(targetDataSources);
        dataSource.setDefaultTargetDataSource(writeDruidDataSource);
        return dataSource;
    }
}
