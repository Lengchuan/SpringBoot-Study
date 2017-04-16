# SpringBoot使用阿里数据库连接池Druid以及多数据源配置

## 引入依赖
```aidl
<!-- https:dataSource
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>druid</artifactId>
    <version>1.0.29</version>
</dependency>

```
## 数据源配置
+ 1.数据源
    ```aidl
    #master数据源
    spring.datasource.master.url=jdbc:mysql://localhost:3306/SpringBootMaster
    spring.datasource.master.username=root
    spring.datasource.master.password=1
    spring.datasource.master.driver-class-name=com.mysql.jdbc.Driver
    spring.datasource.master.masterMapperLocations=classpath:mapper/master/*.xml
    
    
    #cluster数据源
    spring.datasource.cluster.url=jdbc:mysql://localhost:3306/SpringBootCluster
    spring.datasource.cluster.username=root
    spring.datasource.cluster.password=1
    spring.datasource.cluster.driver-class-name=com.mysql.jdbc.Driver
    spring.datasource.cluster.clusterMapperLocations=classpath:mapper/cluster/*.xml
    
    
    #cluster1数据源
    spring.datasource.cluster1.url=jdbc:mysql://localhost:3306/SpringBootCluster1
    spring.datasource.cluster1.username=root
    spring.datasource.cluster1.password=1
    spring.datasource.cluster1.driver-class-name=com.mysql.jdbc.Driver
    spring.datasource.cluster1.clusterMapperLocations=classpath:mapper/cluster1/*.xml
    
    
    ```
    master数据源:
    ```aidl
    @Configuration
    @MapperScan(basePackages = {"com.lc.springBoot.druid.mapper.master"},
            sqlSessionFactoryRef = "masterSqlSessionFactory")
    public class MasterDruidDataSourceConfig {
    
        @Value("${spring.datasource.master.masterMapperLocations}")
        private String masterMapperLocations;
    
        @ConfigurationProperties(prefix = "spring.datasource.master")
        @Bean(name = "masterDataSource")
        @Primary
        public DataSource masterDataSource() {
            return new DruidDataSource();
        }
    
        /**
         * SqlSessionFactory配置
         *
         * @return
         * @throws Exception
         */
        @Bean(name = "masterSqlSessionFactory")
        @Primary
        public SqlSessionFactory masterSqlSessionFactory(
                @Qualifier("masterDataSource") DataSource dataSource
        ) throws Exception {
            SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
            sqlSessionFactoryBean.setDataSource(dataSource);
    
            PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            // 配置mapper文件位置
            sqlSessionFactoryBean.setMapperLocations(resolver.getResources(masterMapperLocations));
    
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
        @Bean(name = "masterTransactionManager")
        @Primary
        public DataSourceTransactionManager masterTransactionManager(
                @Qualifier("masterDataSource") DataSource dataSource
        ) {
            DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
            dataSourceTransactionManager.setDataSource(dataSource);
            return dataSourceTransactionManager;
        }
    }
    ```
    
    cluster数据源:
    ```aidl
    @Configuration
    @MapperScan(basePackages = {"com.lc.springBoot.druid.mapper.cluster"},
            sqlSessionFactoryRef = "clusterSqlSessionFactory")
    public class ClusterDruidDataSourceConfig {
    
        @Value("${spring.datasource.cluster.clusterMapperLocations}")
        private String clusterMapperLocations;
    
        @ConfigurationProperties(prefix = "spring.datasource.cluster")
        @Bean(name = "clusterDataSource")
        public DataSource clusterDataSource() {
            return new DruidDataSource();
        }
    
        /**
         * SqlSessionFactory配置
         *
         * @return
         * @throws Exception
         */
        @Bean(name = "clusterSqlSessionFactory")
        public SqlSessionFactory clusterSqlSessionFactory(
                @Qualifier("clusterDataSource") DataSource dataSource
        ) throws Exception {
            SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
            sqlSessionFactoryBean.setDataSource(dataSource);
    
            PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            //配置mapper文件位置
            sqlSessionFactoryBean.setMapperLocations(resolver.getResources(clusterMapperLocations));
    
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
        @Bean(name = "clusterTransactionManager")
        public DataSourceTransactionManager clusterTransactionManager(
                @Qualifier("clusterDataSource") DataSource dataSource
        ) {
            DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
            dataSourceTransactionManager.setDataSource(dataSource);
            return dataSourceTransactionManager;
        }
    }
    
    ```
    cluster1数据源:
    ```aidl
    @Configuration
    @MapperScan(basePackages = {"com.lc.springBoot.druid.mapper.cluster1"},
            sqlSessionFactoryRef = "cluster1SqlSessionFactory")
    public class Cluster1DruidDataSourceConfig {
    
        @Value("${spring.datasource.cluster1.clusterMapperLocations}")
        private String cluster1MapperLocations;
    
        @ConfigurationProperties(prefix = "spring.datasource.cluster1")
        @Bean(name = "cluster1DataSource")
        public DataSource cluster1DataSource() {
            return new DruidDataSource();
        }
    
        /**
         * SqlSessionFactory配置
         *
         * @return
         * @throws Exception
         */
        @Bean(name = "cluster1SqlSessionFactory")
        public SqlSessionFactory cluster1SqlSessionFactory(
                @Qualifier("cluster1DataSource") DataSource dataSource
        ) throws Exception {
            SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
            sqlSessionFactoryBean.setDataSource(dataSource);
    
            PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            //配置mapper文件位置
            sqlSessionFactoryBean.setMapperLocations(resolver.getResources(cluster1MapperLocations));
    
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
        @Bean(name = "cluster1TransactionManager")
        public DataSourceTransactionManager cluster1TransactionManager(
                @Qualifier("cluster1DataSource") DataSource dataSource
        ) {
            DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
            dataSourceTransactionManager.setDataSource(dataSource);
            return dataSourceTransactionManager;
        }
    }
    
    ```
+ 2.相关注解说明

    @MapperScan:basePackages属性配置需要扫描的mybatis的mapper文件位置,sqlSessionFactory属性配置具体的
    sqlSessionFactory.
    
    @ConfigurationProperties:读取并且设置我们在application.properties配置的内容.

    @Primary:这个注解用来标识当存在多个相同的类型的bean时,优先选用哪个bean注入,需要注意的是,配置多数据源的时候,必须有一个
         且只能有一个@Primary注解.
         

## 读写分离实现

### read数据源配置
    ```
    @Configuration
    @MapperScan(basePackages = {"com.lc.springBoot.druid.mapper.read1"},
            sqlSessionFactoryRef = "read1SqlSessionFactory")
    public class Read1DruidDataSourceConfig {
    
        @Value("${spring.datasource.read1.read1MapperLocations}")
        private String read1MapperLocations;
    
        @ConfigurationProperties(prefix = "spring.datasource.read1")
        @Bean(name = "read1DataSource")
        public DataSource read1DataSource() {
            return new DruidDataSource();
        }
    
        /**
         * SqlSessionFactory配置
         *
         * @return
         * @throws Exception
         */
        @Bean(name = "read1SqlSessionFactory")
        public SqlSessionFactory read1SqlSessionFactory(
                @Qualifier("read1DataSource") DataSource dataSource
        ) throws Exception {
            SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
            sqlSessionFactoryBean.setDataSource(dataSource);
    
            PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            // 配置mapper文件位置
            sqlSessionFactoryBean.setMapperLocations(resolver.getResources(read1MapperLocations));
    
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
        @Bean(name = "read1TransactionManager")
        public DataSourceTransactionManager read1TransactionManager(
                @Qualifier("read1DataSource") DataSource dataSource
        ) {
            DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
            dataSourceTransactionManager.setDataSource(dataSource);
            return dataSourceTransactionManager;
        }
    }

    ```

### write数据源配置
    ```
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
    ```
### 自定义注入AbstractRoutingDataSource
    ```
    @Configuration
    public class DataSourceConfig {
    
        private final static String WRITE_DATASOURCE_KEY = "writeDataSource";
        private final static String READ1_DATASOURCE_KEY = "read1DataSource";
        private final static String READ2_DATASOURCE_KEY = "read2DataSource";
    
        @Bean
        public AbstractRoutingDataSource routingDataSource(
                @Qualifier("writeDataSource") DataSource  writeDataSource,
                @Qualifier("read1DataSource") DataSource  read1DataSource,
                @Qualifier("read2DataSource") DataSource  read2DataSource
        ) {
            DynamicDataSource dataSource = new DynamicDataSource();
            Map<Object, Object> targetDataSources = new HashMap();
            targetDataSources.put(WRITE_DATASOURCE_KEY, writeDataSource);
            targetDataSources.put(READ1_DATASOURCE_KEY, read1DataSource);
            targetDataSources.put(READ2_DATASOURCE_KEY, read2DataSource);
            dataSource.setTargetDataSources(targetDataSources);
            dataSource.setDefaultTargetDataSource(writeDataSource);
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
        @Around("execution(public * com.lc.springBoot.druid.service..*.*(..))")
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
    
### 注解使用
    ```
    @Service
    public class StudentService {
    
        @Autowired
        private StudentMapper studentMapper;
    
        @Transactional
        @TargetDataSource(dataSource = "writeDataSource")
        public boolean createUser(Student student) {
            studentMapper.insert(student);
    
            //事务测试
    //        int i = 1 / 0;
            return true;
        }
    
        @TargetDataSource(dataSource = "read1DataSource")
        public List<Student> getByPage(int page, int rows) {
            Page<Student> studentPage = PageHelper.startPage(page, rows, true);
            List<Student> students = studentMapper.getBypage();
            System.out.println("-------------------" + studentPage.toString() + "-----------");
            return students;
        }
    }

    ```  
## druid监控功能配置

### 配置过滤器
    ```
    /**
     * 配置过滤器,需要拦截哪些url,忽略哪些url,初始化参数等
     *
     * @author lsj <lishuijun1992@gmail.com>
     * @date 17-4-7
     */
    @WebFilter(filterName = "druidStatFilter",//过滤器名称
            urlPatterns = "/",//需要拦截的url
            initParams = {//filter初始化信息
                    //需要忽略的资源
                    @WebInitParam(name = "exclusions", value = "*.js,*.gif,*.jpg," +
                            "*.bmp,*.png,*.css,*.ico,/druid/*"),
                    @WebInitParam(name = "sessionStatEnable", value = "true"),
                    @WebInitParam(name = "profileEnable", value = "true")})
    public class DruidStatFilter extends WebStatFilter {
    }

    ```

### 配置servlet
```
//表明这是一个servlet
@WebServlet(urlPatterns = "/druid/*",//通过哪个url访问
        initParams = {
                @WebInitParam(name = "loginUsername", value = "lengchuan"),//用户名
                @WebInitParam(name = "loginPassword", value = "123456"), //密码
                @WebInitParam(name = "resetEnable", value = "true"),//是否可以重置
                // @WebInitParam(name = "allow",value = "127.0.0.1"),//白名单
                // @WebInitParam(name = "deny",value = "192.168.1.1")//黑名单
        })
public class DruidStatViewServlet extends StatViewServlet {
}

```

### 配置Spring监控
```
@Configuration
public class MyDruidStatInterceptor {

    private static final String[] patterns = new String[]{"com.lc.springBoot.druid.service.*"};

    @Bean
    public DruidStatInterceptor druidStatInterceptor() {
        return new DruidStatInterceptor();
    }

    /**
     * 切入点
     * @return
     */
    @Bean
    public JdkRegexpMethodPointcut druidStatPointcut() {
        JdkRegexpMethodPointcut druidStatPointcut = new JdkRegexpMethodPointcut();
        druidStatPointcut.setPatterns(patterns);
        return druidStatPointcut;
    }

    /**
     * 配置aop
     * @return
     */
    @Bean
    public Advisor druidStatAdvisor() {
        return new DefaultPointcutAdvisor(druidStatPointcut(), druidStatInterceptor());
    }
}

```
### 访问监控页面
    配置完成后启动项目,访问loccalhost:8080/druid/就可以看到我们的监控页面了,用户名配置的是lengchuan,密码是123456这里需要注意的是,我们只有在
    执行了一次数据库操作后,才能获取到我们的数据源信息.
