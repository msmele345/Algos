package com.algos.mitch.payments

interface Payee {

    val name: String
    val bankAccount: Int

    fun grossPayment(): Double
}