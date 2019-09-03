package com.algos.mitch.redis

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories




@Configuration
@EnableRedisRepositories(basePackages = ["com.algos.mitch.*"])
class RedisConfig {
//
//    @Bean
//    fun jedisConnectionFactory(): JedisConnectionFactory {
//        return JedisConnectionFactory()
//    }
//
//    @Bean
//    fun redisTemplate(): RedisTemplate<AlgorithmResponse, String> {
//        val template = RedisTemplate<AlgorithmResponse, String>()
//        template.setConnectionFactory(jedisConnectionFactory())
//        return template
//    }


    @Bean
    fun redisConnectionFactory(
            redisProperties: RedisProperties): LettuceConnectionFactory {
        return LettuceConnectionFactory(
                redisProperties.redisHost,
                redisProperties.redisPort)
    }

    @Bean
    fun redisTemplate(connectionFactory: LettuceConnectionFactory): RedisTemplate<*, *> {
        val template = RedisTemplate<ByteArray, ByteArray>()
        template.setConnectionFactory(connectionFactory)
        return template
    }


}