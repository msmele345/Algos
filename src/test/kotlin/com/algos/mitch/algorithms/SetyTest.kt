package com.algos.mitch.algorithms

import com.algos.mitch.test_helpers.UnitTest
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.Ignore
import org.junit.Test
import org.junit.experimental.categories.Category

@Category(UnitTest::class)
class SetyTest {


    val subject = Sety(10).apply {
        elements = arrayOf(1, 2, 3, 4, 5)
    }

    @Test
    fun `should have a size`() {
        val subject = Sety(10)

        assertThat(subject.size).isNotNull()
        assertThat(subject.size).isEqualTo(0)
    }

    @Test
    fun `should have an isEmpty property that equals 0 at start`() {
        val subject = Sety(10)

        val actual = subject.isEmpty()
        assertThat(actual).isTrue()
    }

    @Test
    fun `should return true if the collection contains the provided value`() {
        val actual = subject.contains(2)
        assertThat(actual).isTrue()
    }

    @Test
    fun `contains - should return false if collection doesnt contain the provided element`() {

        val actual = subject.contains(6)
        assertThat(actual).isFalse()
    }

    @Test
    fun `indexOf - should return the index of the provided value, if it exists`() {

        val subject = Sety(10).apply {
            elements = arrayOf(1, 2, 3, 4, 5)
            size = 5
        }

        val actual = subject.indexOf(2)
        assertThat(actual).isEqualTo(1)
    }

    @Test
    fun `indexOf - should return -1 if the provided index is less than 0`() {
        val subject = Sety(10).apply {
            elements = arrayOf(1, 2, 3, 4, 5)
            size = 5
        }
        val actual = subject.indexOf(7)
        assertThat(actual).isEqualTo(-1)
    }

    @Test
    fun `add - should extend the set and add the provided element if there is capacity`() {
        val subject = Sety(10).apply {
            elements = arrayOf("string", "another string", "different string")
            size = 3
        }

        val actual = subject.add("weird string")

        assertThat(subject.size).isEqualTo(4)
        assertThat(subject.elements).contains("weird string")


    }


    @Test
    fun `remove  - should descrese the size of the backed up array and remove the provided element`() {
        val subject = Sety(10).apply {
            elements = arrayOf("string", "another string", "different string")
            size = 3
        }

        val actual = subject.remove("different string")

        assertThat(actual).isTrue()
        assertThat(subject.elements).doesNotContain("different string")
        assertThat(subject.size).isEqualTo(2)
    }

    @Test
    fun `set - should replace the element with the input element at the give index`() {
        val subject = Sety(10).apply {
            elements = arrayOf("string", "another string", "different string")
            size = 3
        }

        val inputString = "new string"

        val actual = subject.set(1, inputString)

        assertThat(subject.elements).contains(inputString)
        val stringToBeReplaced = "another string"
        assertThat(subject.elements).doesNotContain(stringToBeReplaced)
        assertThat(subject.size).isEqualTo(3)
    }

    @Test
    fun `set - should throw an index out of bounds exception if the provided index is not valid`() {
        val subject = Sety(10).apply {
            elements = arrayOf("string", "another string", "different string")
            size = 3
        }

        val inputString = "new string"
        assertThatThrownBy {
            subject.set(6, inputString)
        }.isInstanceOf(IndexOutOfBoundsException::class.java)
            .satisfies {
                assertThat(it.localizedMessage).isEqualTo("Index 6 is not valid")
            }
    }


//    @Test
//    fun `add - should ac`() {
//        val subject = Sety(10).apply {
//            elements = arrayOf("string", "another string", "different string", "!", "2", "3", "4")
//            size = 7
//        }
//
//        val actual = subject.add("weird string")
//
//        assertThat(subject.size).isEqualTo(10)
//        assertThat(subject.elements.last()).isEqualTo("weird string")
//    }
}