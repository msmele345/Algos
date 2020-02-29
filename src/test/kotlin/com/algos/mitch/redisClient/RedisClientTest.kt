package com.algos.mitch.redisClient

//import com.algos.mitch.algo_store.AlgorithmResponse
//import com.algos.mitch.algo_store.Tag
//import com.algos.mitch.services.AlgorithmDbFaultResolver
//import com.algos.mitch.redis.CacheRepository
//import com.algos.mitch.result.*
//import com.algos.mitch.test_helpers.UnitTest
//import com.nhaarman.mockito_kotlin.*
//import org.assertj.core.api.Assertions.assertThat
//import org.junit.Test
//import org.junit.experimental.categories.Category
//import succeedsAnd
//import java.lang.RuntimeException
//import java.util.*
//
//@Category(UnitTest::class)
//class RedisClientTest {
//
//    val mockRepo: CacheRepository = mock()
//
//    val mockAlgoDbFaultResolver: AlgorithmDbFaultResolver<AlgorithmResponse> = mock()
//
//    val subject = RedisClient(mockAlgoDbFaultResolver, mockRepo)
//
//    @Test
//    fun `findAllAlgos - should call the repository findAll`() {
//
//        subject.findAllAlgos()
//
//        verify(mockRepo, times(1)).findAll()
//    }
//
//    @Test
//    fun `findAllAlgos - should return all algos in the cache`() {
//
//        val mockCache: CacheRepository = mock {
//            on { findAll() } doReturn listOf(
//                generateAlgorithm(),
//                generateAlgorithm("palindrome")
//            )
//        }
//
//        val expected = listOf(
//            generateAlgorithm(),
//            generateAlgorithm("palindrome")
//        )
//
//        val subject = RedisClient(mockAlgoDbFaultResolver, mockCache)
//
//        val actual = subject.findAllAlgos()
//
//        assertThat(actual).containsExactlyInAnyOrderElementsOf(expected)
//
//    }
//
//    @Test
//    fun `findAlgoByName - should invoke the fault resolver when called`() {
//
//        val algoNameId = "hello world"
//
//        subject.findAlgoByName(algoNameId)
//
//        argumentCaptor<() -> Optional<AlgorithmResponse>>().let { captor ->
//            verify(mockAlgoDbFaultResolver).invoke(captor.capture())
//        }
//
//    }
//
//    @Test
//    fun `findAlgoByName - should return the correct algorithm response given a valid name currently in the cache`() {
//
//        val expected = generateAlgorithm("palindrome")
//
//        val mockFaultResolver = mock<AlgorithmDbFaultResolver<AlgorithmResponse>> {
//            on { invoke { any() } }.thenAnswer { answer ->
//                val function = answer.getArgument<() -> Any>(0)
//                return@thenAnswer Success(function())
//            }
//        }
//
//        val mockRedis: CacheRepository = mock {
//            on { findById(any()) } doReturn Optional.of(expected)
//        }
//        val subject = RedisClient(mockFaultResolver, mockRedis)
//        val actual = subject.findAlgoByName("palindrome")
//
//        val captor = argumentCaptor<() -> Optional<AlgorithmResponse>>()
//
//        verify(mockFaultResolver).invoke(captor.capture())
//        assertThat(captor.firstValue).isPresent
//    }
//
//
//    @Test
//    fun `findAlgoByName - should return the correct algorithm response with categories and labels from the repo`() {
//        val expected = generateAlgorithm(name = "array counter", category = com.algos.mitch.algo_store.Category(
//            "easy",
//            difficultlyLevel = 2,
//            tags = listOf(Tag("arrays"))
//        ))
//
//        //repo returns the optional
//        val mockRedis: CacheRepository = mock {
//            on { findById(any()) } doReturn Optional.of(expected)
//        }
//
//        //fault resolver returns the success
//        val mockFaultResolver = mock<AlgorithmDbFaultResolver<AlgorithmResponse>> {
//            on { invoke { any() } } doReturn Success(expected)
//        }
//
//        val subject = RedisClient(mockFaultResolver, mockRedis)
//
//        subject.findAlgoByName("array counter").succeedsAnd { actual ->
//            assertThat(actual).isEqualTo(expected)
//        }
//
//    }
//
//    @Test
//    fun `putAlgorithm - should invoke the repository method save with the given algorithm`() {
//        val inputNewAlgorithm = generateAlgorithm("newReversedString")
//
//        whenever(mockRepo.save(any<AlgorithmResponse>())) doReturn inputNewAlgorithm
//
//        val actual = subject.putAlgorithm(inputNewAlgorithm)
//
//        val captor = argumentCaptor<AlgorithmResponse>()
//
//        verify(mockRepo, times(1)).save(captor.capture())
//
//        assertThat(captor.firstValue).isEqualTo(inputNewAlgorithm)
//        assertThat(actual).isEqualTo(Success(inputNewAlgorithm))
//    }
//
//    @Test
//    fun `putAlgorithm - should return a service error if there is a runtime exception when attempting to write`() {
//        val inputNewAlgorithm = generateAlgorithm("newReversedString")
//
//        whenever(mockRepo.save(any<AlgorithmResponse>())) doThrow RuntimeException("some exception")
//
//        val expected = Failure(serviceErrorOf(ServiceError(
//            service = ServiceName.REDIS,
//            errorMessage = "some exception"
//        )))
//
//        val actual = subject.putAlgorithm(inputNewAlgorithm)
//
//        assertThat(actual).isEqualTo(expected)
//    }
//
//
//    private fun generateAlgorithm(
//        name: String = "hello world",
//        category: com.algos.mitch.algo_store.Category = com.algos.mitch.algo_store.Category(
//            categoryName = "HARD",
//            difficultlyLevel = 3,
//            tags = listOf(Tag("string formatting"))
//        ),
//        codeSnippet: String = "",
//        isSolved: Boolean = false
//
//    ): AlgorithmResponse {
//        return AlgorithmResponse(name, codeSnippet, category, isSolved)
//    }
//}
    /*
     * An invocation on a mock.
     * A placeholder for mock, the method that was called and the arguments that were passed.
     * Returns casted argument at the given index.
     * Can lookup in expanded arguments form {@link #getArguments()}.
* */
