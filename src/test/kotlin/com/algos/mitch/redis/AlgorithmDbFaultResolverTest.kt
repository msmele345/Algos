package com.algos.mitch.redis

import com.algos.mitch.algo_store.AlgorithmResponse
import com.algos.mitch.result.*
import com.algos.mitch.test_helpers.UnitTest
import com.nhaarman.mockito_kotlin.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.experimental.categories.Category
import java.util.*

@Category(UnitTest::class)
class AlgorithmDbFaultResolverTest {


    @Test
    fun `invoke - should call the db when invoked`() {

        val subject = AlgorithmDbFaultResolver<Optional<AlgorithmResponse>>()

        val fakeDbCall: () -> Optional<AlgorithmResponse> = mock()

        whenever(fakeDbCall.invoke()) doReturn Optional.of(AlgorithmResponse("palindrome"))

        subject(fakeDbCall).let {
            verify(fakeDbCall, times(1)).invoke()
        }
    }


    @Test
    fun `invoke - should return a Success of an Algorithm response when the function call response is present`() {
        val subject = AlgorithmDbFaultResolver<Optional<AlgorithmResponse>>()
        val expectedAlgorithmResponse = AlgorithmResponse("palindrome")

        //alternative way to mock w/kotlin mockito
        val fakeDbCall: () -> Optional<AlgorithmResponse> = mock {
            on { invoke() } doReturn Optional.of(expectedAlgorithmResponse)
        }

        subject(fakeDbCall).let { result ->
            assertThat(result).isEqualTo(Success(expectedAlgorithmResponse))
        }
    }

    @Test
    fun `invoke - should return a Failure with a ServiceError when the DB call throws an exception`() {
        val subject = AlgorithmDbFaultResolver<Optional<AlgorithmResponse>>()

        val fakeDbCall: () -> Optional<AlgorithmResponse> = mock()

        val expected = Failure(serviceErrorOf(ServiceError(service = ServiceName.REDIS, errorMessage = "some exception")))

        whenever(fakeDbCall.invoke()) doThrow RuntimeException("some exception")

        subject(fakeDbCall).let { result ->
            assertThat(result).isEqualTo(expected)
        }
    }
}