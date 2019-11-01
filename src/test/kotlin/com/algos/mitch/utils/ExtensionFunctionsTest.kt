package com.algos.mitch.utils

import com.algos.mitch.algo_store.AlgorithmRequest
import com.algos.mitch.test_helpers.UnitTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.experimental.categories.Category

@Category(UnitTest::class)
class ExtensionFunctionsTest {

    @Test
    fun `normalizeRequest - should camelCase a request string if it is more than one word`() {
        val inputRequest = AlgorithmRequest(nameId = "sort array boo")

        val expected = "sortArrayBoo"

        val actual = inputRequest.nameId.normalizeRequest()

        assertThat(actual).isEqualTo(expected)
    }


    @Test
    fun `normalizeRequest - should handle single word requests and decapitalize`() {
        val inputRequest = AlgorithmRequest(nameId = "Bacon")

        val expected = "bacon"

        val actual = inputRequest.nameId.normalizeRequest()

        assertThat(actual).isEqualTo(expected)
    }
}