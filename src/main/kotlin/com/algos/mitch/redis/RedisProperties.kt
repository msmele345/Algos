package com.algos.mitch.redis

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

@Configuration
class RedisProperties(
        @Value("\${spring.redis.port}")
        var redisPort: Int,
        @Value("\${spring.redis.host}")
        var redisHost: String
) {

//    fun RedisProperties(
//            @Value("\${spring.redis.port}") redisPort: Int,
//            @Value("\${spring.redis.host}") redisHost: String): ??? {
//        this.redisPort = redisPort
//        this.redisHost = redisHost
//    }


}