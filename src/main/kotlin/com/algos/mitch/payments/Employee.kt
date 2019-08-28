package com.algos.mitch.payments

abstract class Employee(
        override val name: String,
        override val bankAccount: Int,
        open val grossWage: Double
) : Payee {

}