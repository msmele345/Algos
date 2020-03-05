package com.algos.mitch.algorithms

import org.junit.Assert.*
import org.junit.Test
import java.io.File

class HackerRankTest {

    val subject = HackerRank()

    @Test
    fun contextLoads() {
        val file = File("/Users/U386637/personal/Algos/src/main/resources/input2.txt").inputStream()

        val listOfLines = mutableListOf<String>()

        val input = file.bufferedReader().useLines {
            lines -> lines.forEach { listOfLines.add(it) }
        }

        println(input)
        //failing on first line
        val processedLines = subject.processLines(listOfLines)
    }
}