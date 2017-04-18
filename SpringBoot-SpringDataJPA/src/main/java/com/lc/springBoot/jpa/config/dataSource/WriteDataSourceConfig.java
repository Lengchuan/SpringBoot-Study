package com.lc.springBoot.jpa.config.dataSource;

/**
 * write数据源配置
 *
 * @author lsj <lishuijun1992@gmail.com>
 * @date 17-4-13
 */
//@Configuration
//@EnableJpaRepositories(value = "com.lc.springBoot.jpa.repository",
//                        entityManagerFactoryRef = "writeEntityManagerFactory",
//                        transactionManagerRef="writeTransactionManager")
public class WriteDataSourceConfig {

//    @Autowired
//    JpaProperties jpaProperties;
//
//    @Autowired
//    @Qualifier("writeDruidDataSource")
//    private DataSource writeDruidDataSource;
//
//    /**
//     * EntityManagerFactory类似于Hibernate的SessionFactory,mybatis的SqlSessionFactory
//     * 总之,在执行操作之前,我们总要获取一个EntityManager,这就类似于Hibernate的Session,
//     * mybatis的sqlSession.
//     * @return
//     */
//    @Bean(name = "writeEntityManagerFactory")
//    @Primary
//    public EntityManagerFactory writeEntityManagerFactory() {
//        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
//        factory.setJpaVendorAdapter(vendorAdapter);
//        factory.setPackagesToScan("com.lc.springBoot.jpa.entity");
//        factory.setDataSource(writeDruidDataSource);//数据源
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
//    @Bean(name = "writeTransactionManager")
//    @Primary
//    public PlatformTransactionManager writeTransactionManager() {
//        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
//        jpaTransactionManager.setEntityManagerFactory(this.writeEntityManagerFactory());
//        return jpaTransactionManager;
//    }
}
