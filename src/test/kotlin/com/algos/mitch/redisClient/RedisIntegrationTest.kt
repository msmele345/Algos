package com.algos.mitch.redisClient

import com.algos.mitch.algorithms.AlgorithmResponse
import com.algos.mitch.redis.CacheRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import redis.embedded.RedisServer

@Ignore
@RunWith(SpringJUnit4ClassRunner::class)
@SpringBootTest(classes = [RedisIntegrationTest.TestConfig::class])
class RedisIntegrationTest {



    @Autowired
    private lateinit var cacheCache: CacheRepository


    @Before
    fun setUp() {

    }

    @After
    fun cleanUp() {
    }


    @Test
    fun `context loads`() {
        val actual = cacheCache.save(AlgorithmResponse())

        assertThat(actual).isNotNull


    }




    @EnableRedisRepositories(basePackages = ["com.algos.mitch.*"])
    @Configuration
    class TestConfig

}
