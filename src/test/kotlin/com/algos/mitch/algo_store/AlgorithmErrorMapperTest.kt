package com.algos.mitch.algo_store

import com.algos.mitch.result.ErrorType
import com.algos.mitch.result.ServiceError
import com.algos.mitch.result.ServiceName
import com.algos.mitch.result.serviceErrorOf
import com.algos.mitch.test_helpers.UnitTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.experimental.categories.Category
import org.springframework.http.HttpStatus

@Category(UnitTest::class)
class AlgorithmErrorMapperTest {


    val subject = AlgorithmErrorMapper()

    @Test
    fun `mapErrors - should map a serviceError with errorType of UNKNOWN to 500 INTERNAL_SERVER_ERROR`() {
        val inputServiceError = serviceErrorOf(ServiceError(
            service = ServiceName.REDIS,
            errorMessage = "some problem",
            errorType = ErrorType.UNKNOWN_ERROR,
            keyInError = ""
        ))

        val actual = subject.mapErrors(inputServiceError)

        assertThat(actual.statusCode).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR)
        assertThat(actual.body).isNotNull
    }

    @Test
    fun `mapErrors - should map a serviceError with type ALGORITHM_NOT_FOUND to 404 NOT_FOUND`() {
        val inputServiceError = serviceErrorOf(ServiceError(
            service = ServiceName.REDIS,
            errorMessage = "Algorithm not found",
            errorType = ErrorType.ALGORITHM_NOT_FOUND,
            keyInError = ""
        ))

        subject.mapErrors(inputServiceError).let { actual ->
            assertThat(actual.statusCode).isEqualTo(HttpStatus.NOT_FOUND)
            assertThat(actual.body).isNotNull
        }
    }

}