# SpingBoot使用fastJson

## 引入依赖
```aidl
<!-- https://mvnrepository.com/artifact/com.alibaba/fastjson -->
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>fastjson</artifactId>
    <version>1.2.29</version>
</dependency>

```
## FastJsonHttpMessageConverter
fastJson主要通过FastJsonHttpMessageConverter和FastJsonHttpMessageConverter4来处理我们的HttpMessageconvert,
这两个类都是对HttpMessageConverter的实现.其中前者支持Spring4.2以前的,后者支持Spring4.2之后的版本.

## 使用fastJson
+ 1.首先我们继承FastJsonHttpMessageConverter4,进行一些自己需要的设置,可以通过FastJsonConfig这个类来设置相关属性
```aidl
public class FastJsonConverter extends FastJsonHttpMessageConverter4 {
}
```
+ 2.我们在我们的启动类里面用@Bean注入HttpMessageConverters,这样我们就可以使用fastJson了
```aidl
@SpringBootApplication
public class App {

    @Bean
    HttpMessageConverters fastJsonHttpMessageConverters() {
        return new HttpMessageConverters(new FastJsonConverter());
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
```

## HttpMessageConverter
HttpMessageConverter这个类定义了消息转换的几个方法,里面最重要的两个方法就是read()和write()方法.这是我们
最终需要调用的两个方法,我们再看Spring已经实现的几个类.
+ 1.AbstractHttpMessageConverter,这个类虽然实现read()和write方法了,但又使用了两个抽象方法readInternal()和
    writeInternal(),这是留给我们去具体的实现细节.
+ 2.GenericHttpMessageConverter这也是一个接口.

+ 3.AbstractGenericHttpMessageConverter继承了AbstractHttpMessageConverter并且实现了GenericHttpMessageConverter,
    这个类其实也什么也没干,然后又又一个自己的writeInternal()方法需要我们具体去实现.
+ 4.AbstractGenericHttpMessageConverter4,它继承自AbstractGenericHttpMessageConverter,实现writeInternal()方法,这个就是具体对read()的实现了,
    然后实现了readInternal()方法,这个方法来自AbstractHttpMessageConverter.

## read()和write()方法
这是HttpMessageconvert最核心的两个方法
+ 1.read()方法用来处理我们接受到的请求信息,比如对参数进行解密,参数验签等,当然这些功能我们也可以用SpringMVC的
    拦截器来实现,在以后会做介绍.
+ 2.write()用来处理我们返回的请求信息,比如对参数加密,参数加签名等.
