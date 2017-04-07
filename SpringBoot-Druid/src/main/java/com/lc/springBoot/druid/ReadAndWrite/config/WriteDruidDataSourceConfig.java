package com.lc.springBoot.druid.ReadAndWrite.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * write节点数据源配置
 *
 * @author lsj <lishuijun1992@gmail.com>
 * @date 17-4-4
 */
@Configuration
@MapperScan(basePackages = {"com.lc.springBoot.druid.mapper.write"},
        sqlSessionFactoryRef = "writeSqlSessionFactory")
public class WriteDruidDataSourceConfig {

    @Value("${spring.datasource.write.writeMapperLocations}")
    private String writeMapperLocations;

    @ConfigurationProperties(prefix = "spring.datasource.write")
    @Bean(name = "writeDataSource")
    public DataSource writeDataSource() {
        return new DruidDataSource();
    }

    /**
     * SqlSessionFactory配置
     *
     * @return
     * @throws Exception
     */
    @Bean(name = "writeSqlSessionFactory")
    public SqlSessionFactory writeSqlSessionFactory(
            @Qualifier("writeDataSource") DataSource dataSource
    ) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        // 配置mapper文件位置
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources(writeMapperLocations));
        return sqlSessionFactoryBean.getObject();
    }

    /**
     * 配置事物管理器
     *
     * @return
     */
    @Bean(name = "writeTransactionManager")
    public DataSourceTransactionManager writeTransactionManager(
            @Qualifier("writeDataSource") DataSource dataSource
    ) {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource);
        return dataSourceTransactionManager;
    }
}
