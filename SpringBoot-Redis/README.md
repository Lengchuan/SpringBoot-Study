# 1.SpringBoot 集成Spring Data Redis
  
# 2.Spring Data Redis简介

Spring Data Redis作为Spring Data的一个模块,在Jedis的基础上对Redis的相关操作进行了更高层次的抽象和封装,
集成了Jedis的相关方法,使用起来更加方便.Spring Data Redis相对与Jedis最主要的区别在于:

+ 2.1使用RedisTemplate来封装相关操作,是我们不必考虑连接的创建/获取,关闭/回收这些细节,把关注点集中到业务上来.
+ 2.2抽象出RedisSerializer,可以自由选择或者自定义序列化方式.
+ 2.3对事务操作进行了封装.
+ 2.4将发布/订阅的相关方法进行封装,是开发起来更加方便
除此之外,Spring Data Redis还有很多优势,加上社区的不断努力,将会更加完善,如果你熟悉redis或者jedis,那么
Spring Data Redis对你来说真的很容易掌握.

