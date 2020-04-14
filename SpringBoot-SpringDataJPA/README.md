# SpringBoot集成Spring Data JPA

## JPA是什么

JPA(Java Persistence API)是Sun官方提出的Java持久化规范,它为Java开发人员提供了一种对象/关联映射工具
来管理Java应用中的关系数据.它包括以下几方面的内容:
+ 1.ORM映射
    支持xml和注解方式建立实体与表之间的映射.
+ 2.Java持久化API
    定义了一些常用的CRUD接口,我们只需直接调用,而不需要考虑底层JDBC和SQL的细节.
+ 3.JPQL查询语言
    这是持久化操作中很重要的一个方面，通过面向对象而非面向数据库的查询语言查询数据，避免程序的SQL语句紧密耦合.

    在工作中,我们都会用到ORM技术,比如Hibernate,JOOQ等,根据需求的不同,我们会采用不同的ORM框架,当我们需要
更换ORM框架来满足我们的需求时,由于不同ORM框架的实现,使用方式的区别以及各自为营,我们往往需要对代码进行重构.JPA的
出现就是为了解决这个问题,JPA充分吸收了现有一些ORM框架的优点,具有易于使用，伸缩性强等优点,为ORM技术提供了一套标准的
接口用来整合不同的ORM框架.

## Hibernate对JPA的实现
JPA本身并不做具体的实现,而只是定义了一些接口规范,让其它ORM来具体的实现这些接口,就目前来说,对JPA规范实现最好的就是
Hibernate了.这里提一下Mybatis,Mybatis并没有实现JPA规范,它本身也不能算做一个真正的ORM框架.

## Spring Data JPA是什么
Spring Data JPA只是Spring Data框架的一个模块,可以极大的简化JPA的使用,Spring Data JPA强大的地方还在于能够简化我们
对持久层业务逻辑的开发,通过规范持久层方法的名称,通过名称来判断需要实现什么业务逻辑,我们机会可以在不写一句sql,不做任何dao层
逻辑的情况下完成我们绝大部分的开发,当然,对于一些复杂的,性能要求高的查询,Spring Data JPA一样支持我们使用原生的sql.

在这里我们不过多的去介绍JPA以及Spring Data JPA,主要还是与SpringBoot集成的一些细节以及示例.

## 引入依赖

```aidl
<!-- https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-jpa -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

```
我们引入这个依赖后,发现也引入了Hibernate的包,这是现在一种默认的做法,Hibernate已经被作为JPA规范的最好实现了,这里就不介绍Druid数据源的
配置了,大家可以看另外一篇XXXX.

## 配置我们的数据源以及JPA(Hibernate)
```aidl
#配置模板
#https://docs.spring.io/spring-boot/docs/${springboot.version}/reference/html/common-application-properties.html

#数据源
spring.datasource.druid.write.url=jdbc:mysql://localhost:3306/jpa
spring.datasource.druid.write.username=root
spring.datasource.druid.write.password=1
spring.datasource.druid.write.driver-class-name=com.mysql.jdbc.Driver

spring.datasource.druid.read.url=jdbc:mysql://localhost:3306/jpa
spring.datasource.druid.read.username=root
spring.datasource.druid.read.password=1
spring.datasource.druid.read.driver-class-name=com.mysql.jdbc.Driver

#JPA (JpaBaseConfiguration, HibernateJpaAutoConfiguration)
spring.jpa.database-platform=org.hibernate.dialect.MySQL5Dialect
spring.jpa.database=mysql
spring.jpa.generate-ddl=true
#就是hibernate.hbm2ddl.auto,具体说明可以看README
spring.jpa.hibernate.ddl-auto=update
#通过方法名解析sql的策略,具体说明可以看README,这里就不配置了
spring.jpa.hibernate.naming-strategy=org.hibernate.cfg.DefaultComponentSafeNamingStrategy
spring.jpa.show-sql=true
#spring.jpa.properties.*
#spring.jpa.properties.hibernate.hbm2ddl.auto=update
#spring.jpa.properties.hibernate.show_sql=true
#spring.jpa.properties.hibernate.use-new-id-generator-mappings=true
```

## druid数据源注入
```aidl
@Configuration
public class DruidDataSourceConfig {
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
     * DataSource 配置
     * @return
     */
    @ConfigurationProperties(prefix = "spring.datasource.druid.write")
    @Bean(name = "writeDruidDataSource")
    @Primary
    public DataSource writeDruidDataSource() {
        return new DruidDataSource();
    }
}

```

## EntityManagerFactory实例注入
EntityManagerFactory类似于Hibernate的SessionFactory,mybatis的SqlSessionFactory
总之,在执行操作之前,我们总要获取一个EntityManager,这就类似于Hibernate的Session,
mybatis的sqlSession.
注入EntityManagerFactory有两种方式,一种是直接注入EntityManagerFactory,另一种是通过
LocalContainerEntityManagerFactoryBean来间接注入.虽说这两种方法都是基于
LocalContainerEntityManagerFactoryBean的,但是在配置上还是有一些区别.

+ 1.直接注入EntityManagerFactory

配置:通过spring.jpa.properties.*来配置Hibernate的属性
```aidl
spring.jpa.properties.hibernate.hbm2ddl.auto=update
spring.jpa.properties.hibernate.show_sql=true
spring.jpa.properties.hibernate.use-new-id-generator-mappings=true
```
```aidl
@Configuration
@EnableJpaRepositories(value = "com.lengchuan.springBoot.jpa.repository",
                        entityManagerFactoryRef = "writeEntityManagerFactory",
                        transactionManagerRef="writeTransactionManager")
public class WriteDataSourceConfig {

    @Autowired
    JpaProperties jpaProperties;

    @Autowired
    @Qualifier("writeDruidDataSource")
    private DataSource writeDruidDataSource;

    /**
     * EntityManagerFactory类似于Hibernate的SessionFactory,mybatis的SqlSessionFactory
     * 总之,在执行操作之前,我们总要获取一个EntityManager,这就类似于Hibernate的Session,
     * mybatis的sqlSession.
     * @return
     */
    @Bean(name = "writeEntityManagerFactory")
    @Primary
    public EntityManagerFactory writeEntityManagerFactory() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("com.lengchuan.springBoot.jpa.entity");
        factory.setDataSource(writeDruidDataSource);//数据源

        factory.setJpaPropertyMap(jpaProperties.getProperties());
        factory.afterPropertiesSet();//在完成了其它所有相关的配置加载以及属性设置后,才初始化
        return factory.getObject();
    }

    /**
     * 配置事物管理器
     * @return
     */
    @Bean(name = "writeTransactionManager")
    @Primary
    public PlatformTransactionManager writeTransactionManager() {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(this.writeEntityManagerFactory());
        return jpaTransactionManager;
    }
}
```
+ 2.先注入LocalContainerEntityManagerFactoryBean,再获取EntityManagerFactory

配置:
```aidl
spring.jpa.database-platform=org.hibernate.dialect.MySQL5Dialect
spring.jpa.database=mysql
spring.jpa.generate-ddl=true
#就是hibernate.hbm2ddl.auto,具体说明可以看README
spring.jpa.hibernate.ddl-auto=update
#通过方法名解析sql的策略,具体说明可以看README,这里就不配置了
spring.jpa.hibernate.naming-strategy=org.hibernate.cfg.DefaultComponentSafeNamingStrategy
spring.jpa.show-sql=true
```
```aidl
@Configuration
@EnableJpaRepositories(value = "com.lengchuan.springBoot.jpa.repository",
        entityManagerFactoryRef = "writeEntityManagerFactory",
        transactionManagerRef = "writeTransactionManager")
public class WriteDataSourceConfig1 {

    @Autowired
    JpaProperties jpaProperties;

    @Autowired
    @Qualifier("writeDruidDataSource")
    private DataSource writeDruidDataSource;

    /**
     * 我们通过LocalContainerEntityManagerFactoryBean来获取EntityManagerFactory实例
     * @return
     */
    @Bean(name = "writeEntityManagerFactoryBean")
    @Primary
    public LocalContainerEntityManagerFactoryBean writeEntityManagerFactoryBean(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(writeDruidDataSource)
                .properties(jpaProperties.getProperties())
                .packages("com.lengchuan.springBoot.jpa.entity") //设置实体类所在位置
                .persistenceUnit("writePersistenceUnit")
                .build();
        //.getObject();//不要在这里直接获取EntityManagerFactory
    }

    /**
     * EntityManagerFactory类似于Hibernate的SessionFactory,mybatis的SqlSessionFactory
     * 总之,在执行操作之前,我们总要获取一个EntityManager,这就类似于Hibernate的Session,
     * mybatis的sqlSession.
     * @param builder
     * @return
     */
    @Bean(name = "writeEntityManagerFactory")
    @Primary
    public EntityManagerFactory writeEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return this.writeEntityManagerFactoryBean(builder).getObject();
    }

    /**
     * 配置事物管理器
     * @return
     */
    @Bean(name = "writeTransactionManager")
    @Primary
    public PlatformTransactionManager writeTransactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(writeEntityManagerFactory(builder));
    }
}

```
对于这个配置
```aidl
   @Bean(name = "writeEntityManagerFactoryBean")
    @Primary
    public LocalContainerEntityManagerFactoryBean writeEntityManagerFactoryBean(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(writeDruidDataSource)
                .properties(jpaProperties.getProperties())
                .packages("com.lengchuan.springBoot.jpa.entity") //设置实体类所在位置
                .persistenceUnit("writePersistenceUnit")
                .build();
        //.getObject();//不要在这里直接获取EntityManagerFactory
    }
```
getObject()方法可以获取到EntityManagerFactory的实例,看似跟第一种没有什么区别,但是我们不能直接用
getObject(),不然会获取不到,报空指针异常.

## 读写分离配置

### 自定义注入AbstractRoutingDataSource
```
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
```
    
### 自定义注解
```
    @Target({ElementType.METHOD, ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    public @interface TargetDataSource {
        String dataSource() default "";//数据源
    }

```

    
### 使用ThreadLocal使数据源与线程绑定
```
    public class DynamicDataSourceHolder {
        //使用ThreadLocal把数据源与当前线程绑定
        private static final ThreadLocal<String> dataSources = new ThreadLocal<String>();
    
        public static void setDataSource(String dataSourceName) {
            dataSources.set(dataSourceName);
        }
    
        public static String getDataSource() {
            return (String) dataSources.get();
        }
    
        public static void clearDataSource() {
            dataSources.remove();
        }
    }
```

```
    public class DynamicDataSource extends AbstractRoutingDataSource {
        @Override
        protected Object determineCurrentLookupKey() {
    
            //可以做一个简单的负载均衡策略
            String lookupKey = DynamicDataSourceHolder.getDataSource();
            System.out.println("------------lookupKey---------"+lookupKey);
    
            return lookupKey;
        }
    }
```

### 定义切面
```
    @Aspect
    @Component
    public class DynamicDataSourceAspect {
        @Around("execution(public * com.lengchuan.springBoot.jpa.service..*.*(..))")
        public Object around(ProceedingJoinPoint pjp) throws Throwable {
            MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
            Method targetMethod = methodSignature.getMethod();
            if (targetMethod.isAnnotationPresent(TargetDataSource.class)) {
                String targetDataSource = targetMethod.getAnnotation(TargetDataSource.class).dataSource();
                System.out.println("----------数据源是:" + targetDataSource + "------");
                DynamicDataSourceHolder.setDataSource(targetDataSource);
            }
            Object result = pjp.proceed();//执行方法
            DynamicDataSourceHolder.clearDataSource();
    
            return result;
        }
    }

```
