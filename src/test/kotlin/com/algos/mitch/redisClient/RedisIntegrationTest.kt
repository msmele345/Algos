package com.algos.mitch.redisClient

import com.algos.mitch.algorithms.AlgorithmResponse
import com.algos.mitch.redis.CacheRepository
import com.algos.mitch.test_helpers.IntegrationTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.experimental.categories.Category
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import java.util.*

@RunWith(SpringJUnit4ClassRunner::class)
@SpringBootTest(classes = [RedisIntegrationTest.TestConfig::class])
@Category(IntegrationTest::class)
class RedisIntegrationTest {


    @Autowired
    private lateinit var redisCache: CacheRepository


    @Before
    fun setUp() {
        redisCache.save(AlgorithmResponse("palindrome"))
        redisCache.save(AlgorithmResponse("reverseString"))
    }

    @After
    fun cleanUp() {
        redisCache.deleteAll()
    }


    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    fun `should find all existing algorithms in the cache`() {

        val actual = redisCache.findAll()

        assertThat(actual).hasSize(2)
        assertThat(actual.first().name).isEqualTo("reverseString")
        assertThat(actual.last().name).isEqualTo("palindrome")


    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    fun `should find a specific algorithm by name`() {

        val expected = AlgorithmResponse("palindrome")

        val actual = redisCache.findById("palindrome")

        assertThat(actual).isEqualTo(Optional.of(expected))
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    fun `should return an empty result if the give algo name is not currently in the db`() {

        val actual = redisCache.findById("bacon")

        assertThat(actual).isNotPresent
    }


    @EnableRedisRepositories(basePackages = ["com.algos.mitch.*"])
    @Configuration
    class TestConfig {

        @Bean
        fun redisConnectionFactory(): LettuceConnectionFactory {
            return LettuceConnectionFactory("localhost", 6379)
        }

        @Bean
        fun redisTemplate(connectionFactory: LettuceConnectionFactory): RedisTemplate<*, *> {
            return RedisTemplate<ByteArray, ByteArray>().apply {
                setConnectionFactory(connectionFactory)
                setEnableTransactionSupport(true)
            }
        }

    }

}
