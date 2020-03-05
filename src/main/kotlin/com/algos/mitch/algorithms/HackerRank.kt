package com.algos.mitch.algorithms

class HackerRank {

//    val a: IndentAnd<Int> = IndentAnd(1, "boo").map {
//        it.toInt()
//    }



    fun printConveration(flatTree: List<IndentAnd<InputLine>>, userAnswers: List<String>) {

        flatTree.forEach {indentAndObj ->
            when(indentAndObj.item) {
                is Output -> println("Hopper: ${indentAndObj.item.text}")
                is Answer -> println("User: ${indentAndObj.item.answer}")
                is Conclusion -> println("Conclusion: ${indentAndObj.item.text}")
            }

        }
    }


    fun readLines() : List<String> = generateSequence(::readLine).toList()


//    fun processLine2(line: String): IndentAnd<InputLine> {
//        when(line.first()) {
//            " " ->
//        }
//    }

    //any number of outputs may follow a single output as long as same indenxt
    //amn output willalways be followed by answer
    //an answer can only be followed byt an output //conclusion cannt be

    fun makeOutput(line: String, digits: String = ""): Output {
        val char = line.first()
        return when {
            char.isDigit() -> makeOutput(line.drop(1), digits + char)
            char == ':' && digits.isNotEmpty() -> Output(line.drop(1), digits.toInt())
            else -> Output(digits + line, null)
        }
    }

    fun processLine(line: String) : IndentAnd<InputLine> {
        tailrec fun process(line: String, count: Int): IndentAnd<String> =
            when(line.first()) {
                ' ' -> process(line.drop(1), count + 1)
                else -> IndentAnd(count, line)
            }
        fun makeInputLine(line: String) : InputLine {
            makeOutput(line)

            return when (line.first()) {
                '-' -> Answer(line.drop(1))
                '>' -> Goto(line.drop(1).toInt())
                '=' -> Conclusion(line.drop(1))
                else -> {
                    val char = line.first()
                    if(char.isDigit()) {
                    val (number, newLine) = line.split(":")
                        return Output(newLine, number.toInt())
                    }

                    return Output(line)
                }
            }
        }
        return process(line, 0).map(::makeInputLine)
    }


    fun processLines(lines: List<String>) : Pair<List<IndentAnd<InputLine>>, List<String>> {
        return Pair(
            lines.takeWhile { it != "---" }.map(::processLine),
            lines.dropWhile { it != "---" }.drop(1)
        )
    }
}


data class IndentAnd<T>(val indent: Int, val item: T) {
    fun<S> map(f: (T) -> S): IndentAnd<S> = IndentAnd(indent, f(item))
}

sealed class InputLine
data class Output(val text: String, val lable: Int? = null): InputLine()
data class Answer(val answer: String): InputLine()

sealed class Action: InputLine()
data class Goto(val label: Int) : Action()
data class Conclusion(val text: String) : Action()
