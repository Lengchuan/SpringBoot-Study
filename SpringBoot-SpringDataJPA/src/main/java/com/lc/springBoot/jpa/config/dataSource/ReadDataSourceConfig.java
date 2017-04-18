package com.lc.springBoot.jpa.config.dataSource;

/**
 * write数据源配置
 *
 * @author lsj <lishuijun1992@gmail.com>
 * @date 17-4-13
 */
//@Configuration
//@EnableJpaRepositories(value = "com.lc.springBoot.jpa.repository",
//        entityManagerFactoryRef ="readEntityManagerFactory",
//        transactionManagerRef = "readTransactionManager")
public class ReadDataSourceConfig {

//    @Autowired
//    private JpaProperties jpaProperties;
//
//    @Autowired
//    @Qualifier("readDruidDataSource")
//    private DataSource readDruidDataSource;
//
//    /**
//     * EntityManagerFactory类似于Hibernate的SessionFactory,mybatis的SqlSessionFactory
//     * 总之,在执行操作之前,我们总要获取一个EntityManager,这就类似于Hibernate的Session,
//     * mybatis的sqlSession.
//     * @return
//     */
//    @Bean(name = "readEntityManagerFactory")
//    public EntityManagerFactory readEntityManagerFactory() {
//        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
//        factory.setJpaVendorAdapter(vendorAdapter);
//        factory.setPackagesToScan("com.lc.springBoot.jpa.entity");//要扫描的实体
//        factory.setDataSource(readDruidDataSource);//数据源
//        Properties properties = new Properties();
//        properties.setProperty("hibernate.ddl-auto","true");
//        factory.setJpaProperties(properties);
//
//        factory.setJpaPropertyMap(jpaProperties.getProperties());
//        factory.afterPropertiesSet();//在完成了其它所有相关的配置加载以及属性设置后,才初始化
//        return factory.getObject();
//    }
//
//    /**
//     * 配置事物管理器
//     * @return
//     */
//    @Bean(name = "readTransactionManager")
//    public PlatformTransactionManager readTransactionManager() {
//        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
//        jpaTransactionManager.setEntityManagerFactory(this.readEntityManagerFactory());
//        return jpaTransactionManager;
//    }
}
