# SpringBoot学习--HellWorld

## 引入pom依赖

+ 1.spring-boot-starter-parent可以提供依赖管理,引入以后其它的的starter依赖就可以不用配置版本号.
```$xslt
  <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>1.4.0.RELEASE</version>
    </parent>
```
但是这里也有一个问题,如果我们已经有一个自己的parent,那么我们就不能在这么引用了.我们可以使用下面的方式:
```$xslt
 <dependencyManagement>
        <dependencies>
            <dependency>
                <!-- Import dependency management from Spring Boot -->
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-dependencies</artifactId>
                <version>1.4.0.RELEASE</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>
```
+ 2.spring-boot-starter-web提供了webmvc,tomcat等web开发相关模块
```$xslt
<dependencies>  
    <dependency>  
          <groupId>org.springframework.boot</groupId>  
          <artifactId>spring-boot-starter-web</artifactId>  
      </dependency>  
  </dependencies>  

```

## 开始HelloWorld

### 一个错误的示例
我们需要一个main作为入口来启动我们的项目,但这里我们需要注意我们启动的主类不能放在default包下,
不然会遇到很多莫名的问题,比如下面这个:
```$xslt
2017-03-26 20:06:27.320  WARN 8832 --- [           main] ationConfigEmbeddedWebApplicationContext : Exception encountered during context initialization - cancelling refresh attempt: org.springframework.beans.factory.BeanDefinitionStoreException: Failed to parse configuration class [HelloWorldAppTest1]; nested exception is org.springframework.context.annotation.ConflictingBeanDefinitionException: Annotation-specified bean name 'errorPageFilter' for bean class [org.springframework.boot.web.support.ErrorPageFilter] conflicts with existing, non-compatible bean definition of same name and class [org.springframework.boot.context.web.ErrorPageFilter]
2017-03-26 20:06:27.330 ERROR 8832 --- [           main] o.s.boot.SpringApplication               : Application startup failed

org.springframework.beans.factory.BeanDefinitionStoreException: Failed to parse configuration class [HelloWorldAppTest1]; nested exception is org.springframework.context.annotation.ConflictingBeanDefinitionException: Annotation-specified bean name 'errorPageFilter' for bean class [org.springframework.boot.web.support.ErrorPageFilter] conflicts with existing, non-compatible bean definition of same name and class [org.springframework.boot.context.web.ErrorPageFilter]
```
### 编写我们的第一个HelloWorld
```$xslt
@SpringBootApplication
@RestController
public class HelloWorldApp2 {

    @RequestMapping("/hello")
    public String hello() {
        return "hello world";
    }

    @RequestMapping("/")
    public String index() {
        return "this is index page";
    }

    /**
     * 用于启动项目
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(HelloWorldApp2.class, args);
    }

}
```
### 启动项目
我们只需要启动main方法就可以在浏览器访问localhost:8080/hello,当然我们也可以使用maven命令来启动,我们需要添加下面的插件
```$xslt
   <build>
          <plugins>
              <plugin>
                  <groupId>org.springframework.boot</groupId>
                  <artifactId>spring-boot-maven-plugin</artifactId>
                  <executions>
                      <execution>
                          <goals>
                              <goal>repackage</goal>
                          </goals>
                      </execution>
                  </executions>
              </plugin>
          </plugins>
      </build>
```
然后使用mvn spring-boot:run就可以启动项目了,除此之外,我们也可以使用jar包的方式来启动,
```aidl
java -jar target/SpringBoot-HelloWorld-2-1.0-SNAPSHOT.jar 
```

## 相关注解
+ 1.@SpringBootApplication可以自动进行一些必要的默认配置,等价于@Configuration,@EnableAutoConfiguration和@ComponentScan
    这三个注解.
+ 2.RestController我们可以直接返回json,当然,从名字我们也知道可以直接用来编写RESTTUL风格的接口,要注意的是这个注解相当于一个@ResponseBody
    注解,我们只能返回json,不能返回View了.

