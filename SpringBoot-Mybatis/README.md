# SpringBoot整合Mybatis

## 引入依赖
```aidl
<!-- https://mvnrepository.com/artifact/mysql/mysql-connector-java -->
<dependency>
    <groupId>org.mybatis.spring.boot</groupId>
    <artifactId>mybatis-spring-boot-starter</artifactId>
    <version>1.2.0</version>
</dependency>

<!-- https://mvnrepository.com/artifact/mysql/mysql-connector-java -->
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>6.0.3</version>
</dependency>
```
## 配置数据源
在application.properties里配置mysql数据源
```aidl
spring.datasource.url=jdbc:mysql://localhost:3306/SpringBootTest
spring.datasource.username=root
spring.datasource.password=1
spring.datasource.driver-class-name=com.mysql.jdbc.Driver

```
## 使用Mybatis
+ 1.创建实体对象
```aidl
public class User implements Serializable {
    private Integer userId;

    private String name;

    private String email;

    private Integer age;

    private Date birthday;
    //getter/setter
}
```
+ 2.配置mybatis
```aidl
@Configuration//声明这是用来配置的类,用来取代xml配置
public class MybatisConfig {

    /**
     * 注入一个默认数据源
     */
    @Autowired
    private DataSource dataSource;

    /**
     * SqlSessionFactory配置
     *
     * @return
     * @throws Exception
     */
    @Bean
    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        //配置mapper文件位置
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    /**
     * 配置事物管理器
     *
     * @return
     */
    @Bean
    public DataSourceTransactionManager transactionManager() {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource);
        return dataSourceTransactionManager;
    }
}

```
```aidl
@SpringBootApplication
@MapperScan(value = "com.lc.springBoot.druid.mapper")//需要扫描的mapper接口所在包
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
```

## 注解说明
+ 1.@MapperScan:配置需要扫描的mapper接口位置,除此之外,我们也可以用MapperScannerConfigurer来配置
    ```aidl
    @Configuration
    //MapperScannerConfigurer执行的比较早，所以必须有下面的注解
    @AutoConfigureAfter(MybatisConfig.class)
    public class MapperScannerConfig {
    
        @Bean
        public MapperScannerConfigurer mapperScannerConfigurer() {
            MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
            mapperScannerConfigurer.setBasePackage("com.lc.springBoot.druid.mapper");
            return mapperScannerConfigurer;
        }
    }
    
    ```
    这里需要注意的是,如果我们使用@MapperScan的话,必须把它放在我们的启动类上,就是App这个类.
+ 2.@Configuration:表明这是一个配置类,用来代替xml配置

## 配置插件
+ 1.PageHelper分页插件配置,这里需要注意下Mybatis版本和PageHelper版本的兼容
    ```aidl
    <!-- https://mvnrepository.com/artifact/com.github.pagehelper/pagehelper -->
    <dependency>
        <groupId>com.github.pagehelper</groupId>
        <artifactId>pagehelper</artifactId>
        <version>4.2.0</version>
    </dependency>

    
    ```
    ```aidl
       @Bean
       public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
           SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
           sqlSessionFactoryBean.setDataSource(dataSource);
      
           PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
           //配置mapper文件位置
           sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));
      
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
    ```