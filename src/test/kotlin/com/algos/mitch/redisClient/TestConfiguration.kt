package com.algos.mitch.redisClient

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.AbstractRedisConnection
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import redis.embedded.RedisServer
import javax.annotation.PostConstruct
import javax.annotation.PreDestroy

@Configuration
class TestConfiguration {


    private var redisServer: RedisServer = RedisServer(6379)

    @PostConstruct
    fun postConstruct() {
        redisServer.start()
    }

    @PreDestroy
    fun preDestroy() {
        redisServer.stop()
    }


    @Bean fun AbstractRedisConnection() : AbstractRedisConnection {
        return AbstractRedisConnection()
    }

    @Bean
    fun redisConnectionFactory(): LettuceConnectionFactory {
        return LettuceConnectionFactory("localhost",6379)
    }

    @Bean
    fun redisTemplate(connectionFactory: LettuceConnectionFactory): RedisTemplate<*, *> {
        val template = RedisTemplate<ByteArray, ByteArray>()
        template.setConnectionFactory(connectionFactory)
        return template
    }

}

//
//<bean id="lettuceConnectionFactory "
//class="org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory">
//<constructor-arg index="0" ref="lettucePool" />
//</bean>
//
//<bean id="lettucePool" class="org.springframework.data.redis.connection.lettuce.DefaultLettucePool">
//<property name="hostName" value="${redis.host}" />
//<property name="port" value="${redis.port}" />
//<property name="poolConfig" ref="lettucePoolConfiguration" />
//</bean>
//
//<bean id="lettucePoolConfiguration" class="org.springframework.data.redis.connection.PoolConfig">
//<property name="maxIdle" value="${redis.maxIdle}" />
//<property name="maxActive" val...
//</bean>