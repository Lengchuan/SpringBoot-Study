# SpringBoot使用Junit

## 引入依赖
```aidl
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>
```
## 测试
+ 1.我们需要测试的类
```aidl
@Service
public class HelloWorldService {
    public void sayHello() {
        System.out.println("Hello world");
    }
}

```
+ 2.测试
```aidl
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class)
public class HelloWorldServiceTest {

    @Autowired
    private HelloWorldService helloWorldService;

    @Test
    public void sayHello() throws Exception {
        helloWorldService.sayHello();
    }

}
```
## 注解说明
+ 1.@RunWith(SpringJUnit4ClassRunner.class)用来引入SpringJunit支持.
+ 2.@SpringBootTest(classes = App.class)用来加载我们的启动类,也就是启动Spring容器,这是1.4之后的
    注解,我们也可以用SpringApplicationConfiguration这个注解.
+ 3.@WebAppConfiguration,如果是web项目,我们再测试时需要加载ServletContext的话,就需要加上这个注解.