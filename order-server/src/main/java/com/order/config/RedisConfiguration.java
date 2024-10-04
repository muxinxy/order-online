package com.order.config;

import org.springframework.boot.autoconfigure.cache.CacheProperties.Redis;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import lombok.extern.slf4j.Slf4j;

/**
 * Redis配置类
 */
@Configuration
@Slf4j
public class RedisConfiguration {

    /**
     * RedisTemplate配置
     * @param redisConnectionFactory
     * @return RedisTemplate
     */
    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        log.info("RedisConfiguration: redisTemplate");
        RedisTemplate redisTemplate = new RedisTemplate();
        // 设置Redis连接工厂对象
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        // 设置Redis序列化器
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        return redisTemplate;
    }

}
