package com.algos.mitch.payments

class SalaryEmployee(
        override val name: String,
        override var bankAccount: Int,
        override val grossWage: Double
) : Employee(name, bankAccount, grossWage) {


    override fun grossPayment(): Double  = grossWage

}