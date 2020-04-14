package com.lengchuan.springBoot.druid.ReadAndWrite.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lengchuan <lishuijun1992@gmail.com>
 * @date 17 -4-5
 */
@Configuration
public class DataSourceConfig {

    private final static String WRITE_DATASOURCE_KEY = "writeDataSource";
    private final static String READ1_DATASOURCE_KEY = "read1DataSource";
    private final static String READ2_DATASOURCE_KEY = "read2DataSource";

    /**
     * 注入AbstractRoutingDataSource
     * @param writeDataSource
     * @param read1DataSource
     * @param read2DataSource
     * @return
     * @throws Exception
     */
    @Bean
    public AbstractRoutingDataSource routingDataSource(
            @Qualifier("writeDataSource") DataSource writeDataSource,
            @Qualifier("read1DataSource") DataSource read1DataSource,
            @Qualifier("read2DataSource") DataSource read2DataSource
    ) throws Exception {
        DynamicDataSource dataSource = new DynamicDataSource();
        Map<Object, Object> targetDataSources = new HashMap();
        targetDataSources.put(WRITE_DATASOURCE_KEY, writeDataSource);
        targetDataSources.put(READ1_DATASOURCE_KEY, read1DataSource);
        targetDataSources.put(READ2_DATASOURCE_KEY, read2DataSource);
        dataSource.setTargetDataSources(targetDataSources);
        dataSource.setDefaultTargetDataSource(writeDataSource);
        return dataSource;
    }
}
