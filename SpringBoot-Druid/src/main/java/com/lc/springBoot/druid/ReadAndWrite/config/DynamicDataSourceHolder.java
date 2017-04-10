package com.lc.springBoot.druid.ReadAndWrite.config;

/**
 * @author lsj <lishuijun1992@gmail.com>
 * @date 17-4-5
 */
public class DynamicDataSourceHolder {
    //使用ThreadLocal把数据源与当前线程绑定
    private static final ThreadLocal<String> dataSources = new ThreadLocal<String>();

    public static void setDataSource(String dataSourceName) {
        dataSources.set(dataSourceName);
    }

    public static String getDataSource() {
        return dataSources.get();
    }

    public static void clearDataSource() {
        dataSources.remove();
    }
}