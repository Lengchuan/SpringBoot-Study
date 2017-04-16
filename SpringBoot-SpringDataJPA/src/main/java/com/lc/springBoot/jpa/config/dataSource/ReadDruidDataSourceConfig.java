package com.lc.springBoot.jpa.config.dataSource;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * write数据源配置
 *
 * @author lsj <lishuijun1992@gmail.com>
 * @date 17-4-13
 */
@Configuration
@EnableJpaRepositories(value = "com.lc.springBoot.jpa.repository",
        entityManagerFactoryRef ="readEntityManagerFactory")
public class ReadDruidDataSourceConfig {

    @Autowired
    private MyJpaProperties jpaProperties;

    /**
     * DataSource 配置
     * @return
     */
    @ConfigurationProperties(prefix = "spring.datasource.druid.read")
    @Bean(name = "readDruidDataSource")
    public DataSource readDruidDataSource() {
        return new DruidDataSource();
    }

    /**
     * EntityManagerFactory类似于Hibernate的SessionFactory,mybatis的SqlSessionFactory
     * 总之,在执行操作之前,我们总要获取一个EntityManager,这就类似于Hibernate的Session,
     * mybatis的sqlSession.
     * @return
     */
    @Bean(name = "readEntityManagerFactory")
    public EntityManagerFactory readEntityManagerFactory() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("com.lc.springBoot.jpa.entity");//要扫描的实体
        factory.setDataSource(this.readDruidDataSource());//数据源

        factory.setJpaPropertyMap(jpaProperties.jpaProperties().getProperties());
        factory.afterPropertiesSet();//在完成了其它所有相关的配置加载以及属性设置后,才初始化
        return factory.getObject();
    }

    /**
     * 配置事物管理器
     * @return
     */
    @Bean(name = "readTransactionManager")
    public PlatformTransactionManager readTransactionManager() {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(this.readEntityManagerFactory());
        return jpaTransactionManager;
    }
}
