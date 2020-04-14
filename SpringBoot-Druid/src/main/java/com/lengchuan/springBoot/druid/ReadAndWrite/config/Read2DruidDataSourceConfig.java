package com.lengchuan.springBoot.druid.ReadAndWrite.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.plugin.Interceptor;
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
import java.util.Properties;

/**
 * read2节点数据源配置
 *
 * @author lengchuan <lishuijun1992@gmail.com>
 * @date 17-4-4
 */
@Configuration
@MapperScan(basePackages = {"com.lengchuan.springBoot.druid.mapper.read2"},
        sqlSessionFactoryRef = "read2SqlSessionFactory")
public class Read2DruidDataSourceConfig {

    @Value("${spring.datasource.read2.read2MapperLocations}")
    private String read2MapperLocations;

    @ConfigurationProperties(prefix = "spring.datasource.read2")
    @Bean(name = "read2DataSource")
    public DataSource read2DataSource() {
        return new DruidDataSource();
    }

    /**
     * SqlSessionFactory配置
     *
     * @return
     * @throws Exception
     */
    @Bean(name = "read2SqlSessionFactory")
    public SqlSessionFactory read2SqlSessionFactory(
            @Qualifier("read2DataSource") DataSource dataSource
    ) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        // 配置mapper文件位置
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources(read2MapperLocations));

        //配置分页插件
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("reasonable", "true");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("returnPageInfo", "check");
        properties.setProperty("params", "count=countSql");
        pageHelper.setProperties(properties);

        //设置插件
        sqlSessionFactoryBean.setPlugins(new Interceptor[]{pageHelper});
        return sqlSessionFactoryBean.getObject();
    }

    /**
     * 配置事物管理器
     *
     * @return
     */
    @Bean(name = "read2TransactionManager")
    public DataSourceTransactionManager read2TransactionManager(
            @Qualifier("read2DataSource") DataSource dataSource
    ) {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource);
        return dataSourceTransactionManager;
    }
}
