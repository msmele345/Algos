#!/usr/bin/env kscript
import java.io.File

println("File:  ${args.first()}")

val file = args.first()

val resourcePath = "/resources/test_data.csv"

fun readFileUsingGetResource(fileName: String) 
  = this::class.java.getResource(fileName).readText(Charsets.UTF_8)


fun readWithKotlin(fileName: String): List<String> {
	return File(fileName).readLines()
}

fun readFileLineByLineUsingForEachLine(fileName: String) 
  = File(fileName).forEachLine { println(it) }

fun readFileAsTextUsingInputStream(fileName: String)
  = File(fileName).inputStream().readBytes().toString(Charsets.UTF_8)

//println(readWithKotlin(file))

//readFileUsingGetResource(resourcePath)

//readFileAsTextUsingInputStream(file)

readFileLineByLineUsingForEachLine(file)
