package com.algos.mitch.algorithms

class LotteryTicket {
    //char.toInt() returns the ASCII code!!


    fun superIdiomKotlinAlternative(ticket: Array<Pair<String, Int>>, win: Int): String {
        return ticket.count { pair -> pair.first.any { char -> char.toInt() == pair.second } }.let { winCount ->
            when (winCount >= win) {
                true -> "Winner!"
                false -> "Looser!"
            }
        }
    }

    fun bingo(ticket: Array<Pair<String, Int>>, win: Int): String {
        var winCount = 0
        ticket.forEach { if (countWins(it)) winCount++ }

        return if (winCount >= win) "Winner!" else "Loser!"
    }

    fun countWins(inputTicket: Pair<String, Int>): Boolean {
        return inputTicket.first.split("").any { letter ->
            mapCodes().let { map ->
                map[letter] == inputTicket.second
            }
        }
    }

    fun mapCodes(): Map<String, Int> {
        return (65..90).toList().let { codes ->
            ('A'..'Z')
                .map { it.toString() }.zip(codes)
        }.toMap()
    }

}