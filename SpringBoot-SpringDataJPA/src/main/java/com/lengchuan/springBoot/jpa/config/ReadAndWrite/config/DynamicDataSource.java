package com.lengchuan.springBoot.jpa.config.ReadAndWrite.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author lengchuan <lishuijun1992@gmail.com>
 * @date 17-4-5
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {

        //可以做一个简单的负载均衡策略
        String lookupKey = DynamicDataSourceHolder.getDataSource();
        System.out.println("------------lookupKey---------"+lookupKey);

        return lookupKey;
    }
}

