package com.algos.mitch.algorithms

import java.lang.StringBuilder

class EasyStockBroker {

    fun formatTrade(tradeConfirm: String): String {
        if (!tradeConfirm.isValidFormat()) throw TradeErrorException("Trade Ticket Error")
        val (sym, qty, price, type) = tradeConfirm.split(" ")
        return StringBuilder().apply {
            append("Buy:")
            append(" ")
            append(if (buyOrder(type)) mapIntAndMultiply(qty, price) else 0)
            append(" ")
            append("Sell:")
            append(" ")
            append(if (!buyOrder(type)) mapIntAndMultiply(qty, price) else 0)
        }.toString()
    }

    fun mapIntAndMultiply(qty: String, price: String): String =
        listOf(qty, price)
            .map { it.toDouble() }
            .sumTotal()

    fun buyOrder(type: String): Boolean = type == "B" || type == "b"

    fun List<Double>.sumTotal(): String {
        return (this.first() * this.last()).run { kotlin.math.abs(toInt()) }.toString()
    }

    fun String.isValidFormat(): Boolean =
        this.split(" ").size == 4
}


class TradeErrorException(message: String) : Throwable(message)