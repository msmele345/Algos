package com.algos.mitch.redis

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
//import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
//import org.springframework.data.redis.core.RedisTemplate
//import org.springframework.data.redis.repository.configuration.EnableRedisRepositories

//@Configuration
//@EnableRedisRepositories(basePackages = ["com.algos.mitch.*"])
//@ComponentScan("com.algos.mitch.*")
//class RedisConfig {
//
//
//    @Bean
//    fun redisConnectionFactory(
//        redisProperties: RedisProperties): LettuceConnectionFactory {
//        return LettuceConnectionFactory(
//            redisProperties.redisHost,
//            redisProperties.redisPort
//        )
//    }
//
//    @Bean
//    fun redisTemplate(connectionFactory: LettuceConnectionFactory): RedisTemplate<*, *> {
//        return RedisTemplate<ByteArray, ByteArray>().apply {
//            setConnectionFactory(connectionFactory)
//            setEnableTransactionSupport(true)
//        }
//    }
//
//}