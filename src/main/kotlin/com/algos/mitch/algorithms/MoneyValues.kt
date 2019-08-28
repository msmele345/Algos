package com.algos.mitch.algorithms

class MoneyValues {


    fun moneyValue(money: String): Double =
            when (money.contains("$") && money.contains("-")) {
                true -> "-${money.replace("$", "").split(" ")[1]}".toDouble()
                false -> money.replace("$", "").toDouble()
            }

}