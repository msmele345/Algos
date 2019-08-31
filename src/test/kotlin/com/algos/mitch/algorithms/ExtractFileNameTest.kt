package com.algos.mitch.algorithms

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class ExtractFileNameTest {

    val subject = ExtractFileName()

    @Test
    fun `extractFileName - should parse the file name and return just the name and first extension`() {

        val expectedResult = "This_is_an_otherExample.mpg"

        val inputFile = "1_This_is_an_otherExample.mpg.OTHEREXTENSIONadasdassdassds34"

        subject.extractFileName(inputFile).let { result ->
            assertThat(result).isEqualTo(expectedResult)
        }


    }


    @Test
    fun `extractFileName2 - should parse the file name and return just the name and first extension`() {

        val expectedResult = "myFile.tar"

        val inputFile = "1231231223123131_myFile.tar.gz2"

        subject.extractFileName(inputFile).let { result ->
            assertThat(result).isEqualTo(expectedResult)
        }
    }

    @Test
    fun `extractFileName3 - should parse the file name and return just the name and first extension`() {

        val expectedResult = "FILE_NAME.EXTENSION"

        val inputFile = "1231231223123131_FILE_NAME.EXTENSION.OTHEREXTENSION"

        subject.extractFileName(inputFile).let { result ->
            assertThat(result).isEqualTo(expectedResult)
        }
    }

    @Test
    fun `removeDate - should parse the inputString and remove the numbers at the beginning`() {
        val inputString = "222222_someFile[33a].mpg"

        val expected = "someFile[33a].mpg"

        subject.removeDate(inputString).let { result ->
            assertThat(result).isEqualTo(expected)
        }
    }


}