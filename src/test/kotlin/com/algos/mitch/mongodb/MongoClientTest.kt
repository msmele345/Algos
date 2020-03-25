package com.algos.mitch.mongodb

import com.algos.mitch.algo_store.*
import com.algos.mitch.result.*
import com.nhaarman.mockito_kotlin.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import failsAnd
import org.assertj.core.api.Assertions.assertThatThrownBy
import succeeds
import succeedsAnd
import java.lang.RuntimeException

class MongoClientTest {

    val mockRepo: AlgorithmMongoRepository = mock()
    val mockSetRepo: SetRepository = mock()
    val mockErrorHandler: FaultResolver<AlgorithmDomainModel?, ServiceErrors> = mock()
    val subject = MongoClient(
        setRepository = mockSetRepo,
        mongoRepository = mockRepo,
        mongoErrorHandler = mockErrorHandler
    )


    val mockAlgorithmDomainModel = AlgorithmDomainModel(
        name = "countDupes",
        codeSnippet = """
            fun countDupes(arr: Array<Int>): Int = arr.size - arr.distinct()
        """.trimIndent(),
        category = Category(
            categoryDescription = "EASY",
            difficultyLevel = 2,
            tags = listOf(Tag("Collections"))
        ),
        isSolved = false
    )

    val mockAlgorithmDomainModel2 = AlgorithmDomainModel(
        name = "some algo",
        codeSnippet = """
            fun someAlgo(arr: Array<Int>): Int = -1
        """.trimIndent(),
        category = Category(
            categoryDescription = "EASY",
            difficultyLevel = 2,
            tags = listOf(Tag("Random"))
        )
    )

    @Test
    fun `getAlgorithmByName - should delegate the call to the errorHandler`() {
        subject.getAlgorithmByName("countDupes").let {
            verify(mockErrorHandler).invoke(any())
        }
    }

    @Test
    fun `getAlgorithmByName - should return a Success if the call to the repo succeeds`() {

        val expected = serviceErrorOf(ServiceError(
            service = ServiceName.MONGO_DB,
            errorType = ErrorType.UNKNOWN_ERROR,
            keyInError = ""
        ))


        whenever(mockErrorHandler.invoke(any())) doReturn Success(mockAlgorithmDomainModel)

        subject.getAlgorithmByName("countDupes").succeedsAnd { result ->
            assertThat(result).isEqualTo(mockAlgorithmDomainModel)
        }
    }


    @Test
    fun `getAlgorithmByName - should return a Failure with serviceErrors if the call to the repo throws an exception`() {

        val expected = serviceErrorOf(ServiceError(
            service = ServiceName.MONGO_DB,
            errorType = ErrorType.UNKNOWN_ERROR,
            keyInError = "countDupes"
        ))

        whenever(mockErrorHandler.invoke(any())) doReturn Failure(expected)

        subject.getAlgorithmByName("countDupes").let { result ->
            assertThat(result).isEqualTo(Failure(expected))
        }
    }

    @Test
    fun `fetchAlgorithms - should call the repository method findAll`() {
        subject.fetchAlgorithms().let {
            verify(mockRepo).findAll()
        }
    }


    @Test
    fun `fetchAlgorithms - should return a Success of Algorithms if there are no errors`() {

        val expected = listOf(
            mockAlgorithmDomainModel,
            mockAlgorithmDomainModel2
        )

        whenever(mockRepo.findAll()) doReturn expected

        subject.fetchAlgorithms().succeedsAnd { result ->
            assertThat(result).isEqualTo(Algorithms(expected))
        }
    }

    @Test
    fun `fetchAlgorithms - should return a Failure of ServiceErrors if the repo throws an exception`() {

        val expected = Failure(serviceErrorOf(ServiceError(
            service = ServiceName.MONGO_DB,
            errorMessage = "something bad happened",
            errorType = ErrorType.UNKNOWN_ERROR
        )))

        whenever(mockRepo.findAll()) doThrow RuntimeException("something bad happened")

        val actual = subject.fetchAlgorithms()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `fetchAlgorithms - algorithmResponse should contain isSolved fields`() {
        val domainAlgorithm = AlgorithmDomainModel(name = "testAlgo", isSolved = true)

        whenever(mockRepo.findAll()) doReturn listOf(domainAlgorithm)

        subject.fetchAlgorithms().succeedsAnd { result ->
            assertThat(result).isEqualTo(Algorithms(listOf(domainAlgorithm)))
        }
    }

    @Test
    fun `createAlgorithm - should invoke the mongo repo insert method`() {
        val newAlgorithm = AlgorithmDomainModel()

        whenever(mockRepo.insert(newAlgorithm)) doReturn newAlgorithm

        val actual = subject.createAlgorithm(newAlgorithm)

        verify(mockRepo).insert(any<AlgorithmDomainModel>())
    }


    @Test
    fun `createAlgorithm - should return a Success containing the inserted Algorithm`() {
        val newAlgorithm = AlgorithmDomainModel()

        whenever(mockRepo.insert(newAlgorithm)) doReturn newAlgorithm

        subject.createAlgorithm(newAlgorithm).succeedsAnd { actual ->
            assertThat(actual).isEqualTo(newAlgorithm)
        }
    }

    @Test
    fun `createAlgorithm - should return a Failure with service errors if mongo fails to insert`() {
        val expected = serviceErrorOf(ServiceError(
            service = ServiceName.MONGO_DB,
            errorMessage = "some error",
            errorType = ErrorType.PERSISTANCE_ERROR
        ))

        whenever(mockRepo.insert(any<AlgorithmDomainModel>())) doThrow RuntimeException("some error")

        subject.createAlgorithm(AlgorithmDomainModel()).failsAnd { serviceError ->
            assertThat(serviceError).isEqualTo(expected)
        }
    }

    @Test
    fun `fetchAllSets - success - shouldReturn a list of CustomSets from repo`() {

        val expected = listOf(
            CustomSet(id = "1", name = "CoolSet"),
            CustomSet(id = "2", name = "JavaSet")
        )
        whenever(mockSetRepo.setGenerator()) doReturn expected

        val actual = subject.fetchAllSets()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `fetchAllSets - failure - should return ServiceErrors if setRepo throws an exception`() {

        whenever(mockSetRepo.setGenerator()) doThrow RuntimeException("some error")

        assertThatThrownBy {
            subject.fetchAllSets()
        }
            .isInstanceOf(RuntimeException::class.java)
            .hasMessage("some error occured")


    }

    @Test
    fun `fetchSetById - success - should return a Success of a Custom set if provided a valid Id`() {

        val expected = CustomSet(id = "1", name = "Sety")

        val listOfSets = listOf(
            CustomSet(id = "1", name = "Sety"),
            CustomSet(id = "2", name = "CoolSet"),
            CustomSet(id = "3", name = "BadSet")
        )

        whenever(mockSetRepo.setGenerator()) doReturn listOf(listOfSets)

        val actual = subject.fetchSetById("1")

        assertThat(actual.succeeds()).isEqualTo(expected)
    }

    @Test
    fun `fetchSetById - failure - should return a Failure with ServiceErrors if set is not found`() {

        val expected = Failure(serviceErrorOf(ServiceError(
            errorType = ErrorType.ALGORITHM_NOT_FOUND,
            errorMessage = "Set not available at this time"
        )))

        whenever(mockSetRepo.setGenerator()) doReturn listOf(
            CustomSet(id = "1", name = "Sety"),
            CustomSet(id = "2", name = "CoolSet")
        )

        subject.fetchSetById("3").let { actual ->
            assertThat(actual).isEqualTo(expected)
        }
    }
}