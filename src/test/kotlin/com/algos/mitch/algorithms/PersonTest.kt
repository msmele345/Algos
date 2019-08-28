package com.algos.mitch.algorithms

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class PersonTest {

    val subject = Person()

    val joe = Person("Joe", "Jones", 45)

    val jane = Person("Jane", "Smith", 12)
    val mary = Person("Mary", "Wilson", 70)
    val john = Person("John", "Adams", 32)
    val jean = Person("Jean", "Smithson", 66)

    val listOfPeople = listOf(joe, jane, mary, john, jean)

    @Test
    fun `should  `() {

        val (fName, lName, age) = joe //data class gives you component functions by default
        val peopleMap = hashMapOf<String, Person>()
        listOfPeople.forEach {
            peopleMap[it.lastName] = it
        }


        val lastNamesWithS = peopleMap.filter { it.value.lastName.startsWith("S") }

        val namePairs = peopleMap.map { Pair(it.value.firstName, it.value.lastName) }

        val firstNames = peopleMap.map { it.value.firstName }
        val lastNames = peopleMap.map { it.value.lastName }

        val namePairs2 = firstNames.zip(lastNames) // zip takes two lists and creates pairs with the values.

        //ALSO GIVES YOU IT
        peopleMap.also {
            it.map {
                println("${it.value.firstName} is ${it.value.age}")
            }
        }


    }


    @Test
    fun `should return a list with common values from the input lists`() {
        val list1 = listOf(1,4,9,15,33)
        val list2 = listOf(4,55,-83,22,15,10)

        val expected = listOf(4,15)

        subject.mergeLists(list1, list2).let {
            assertThat(it).hasSize(2)
            assertThat(it).isEqualTo(expected)
        }

    }


    @Test
    fun `should use covariance and contra-variance examples` () {
        //compare a box of paper to a box of regular paper. Regular is a sub type of page, so we need to accept subtypes
        //if you want to accept sub-types, then use extends out (read) and  CANT WRITE
        //if you want to accept super-types, then use super IN (write) and CANT READ
        var regularPaper = Box<Regular>()
        var paper = Box<Paper>()
        paper = regularPaper
    }


    @Test
    fun `should use run or apply`() {

    }



}
