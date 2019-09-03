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
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import redis.embedded.RedisServer

@Ignore
@RunWith(SpringJUnit4ClassRunner::class)
@SpringBootTest(classes = [TestConfiguration::class])
@EnableRedisRepositories(basePackages = ["com.algos.mitch.*"])
class RedisIntegrationTest {



    @Autowired
    private lateinit var cacheCache: CacheRepository

    @Autowired
    private lateinit var redisServer: RedisServer


    @Before
    fun setUp() {
        redisServer = RedisServer(6379)
        redisServer.start()
    }

    @After
    fun cleanUp() {
        redisServer.stop()
    }


    @Test
    fun `context loads`() {
        val actual = cacheCache.save(AlgorithmResponse())

        assertThat(actual).isNotNull


    }

}