package com.lc.springBoot.redis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * @author lsj <lishuijun1992@gmail.com>
 * @date 17-4-19
 */
@Configuration
public class RedisTemplateConfig {

    @Autowired
    private JedisConnectionFactory jedisConnectionFactory;

    @Bean
    public RedisSerializer fastJsonRedisSerializer() {
        return new FastJsonRedisSerializer(Object.class);
    }

    @Bean
    public RedisTemplate redisTemplate(RedisSerializer redisSerializer) {
        StringRedisTemplate redisTemplate = new StringRedisTemplate(jedisConnectionFactory);
        redisTemplate.setValueSerializer(redisSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
}
