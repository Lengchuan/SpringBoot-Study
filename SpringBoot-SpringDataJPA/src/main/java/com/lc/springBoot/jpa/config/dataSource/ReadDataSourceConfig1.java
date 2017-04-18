package com.lc.springBoot.jpa.config.dataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManager;
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
        entityManagerFactoryRef = "readEntityManagerFactory",
        transactionManagerRef = "readTransactionManager")
public class ReadDataSourceConfig1 {

    @Autowired
    private JpaProperties jpaProperties;

    @Autowired
    @Qualifier("readDruidDataSource")
    private DataSource readDruidDataSource;

    /**
     * EntityManagerFactory类似于Hibernate的SessionFactory,mybatis的SqlSessionFactory
     * 总之,在执行操作之前,我们总要获取一个EntityManager,这就类似于Hibernate的Session,
     * mybatis的sqlSession.
     * @return
     */
    @Bean(name = "readEntityManagerFactoryBean")
    public LocalContainerEntityManagerFactoryBean readEntityManagerFactoryBean(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(readDruidDataSource)
                .properties(jpaProperties.getProperties())
                .packages("com.lc.springBoot.jpa.entity") //设置实体类所在位置
                .persistenceUnit("readPersistenceUnit")//持久化单元名称
                .build();
        //.getObject();
    }

    /**
     *
     * @param builder
     * @return
     */
    @Bean(name = "readEntityManagerFactory")
    public EntityManagerFactory readEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return this.readEntityManagerFactoryBean(builder).getObject();
    }

    /**
     * 配置事物管理器
     * @return
     */
    @Bean(name = "readTransactionManager")
    public PlatformTransactionManager readTransactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(readEntityManagerFactory(builder));
    }
}
