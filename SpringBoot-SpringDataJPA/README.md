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

```
