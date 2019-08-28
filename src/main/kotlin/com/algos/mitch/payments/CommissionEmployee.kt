package com.algos.mitch.payments

data class CommissionEmployee(
        override val name: String,
        override var bankAccount: Int,
        override var grossWage: Double
) : Employee(name, bankAccount, grossWage) {

    var grossCommission: Double = 0.0

    override fun grossPayment(): Double {
        return grossWage + doCurrentCommission()
    }


    private fun doCurrentCommission(): Double {
        val commission = grossCommission
        grossCommission = 0.0
        return commission
    }

    fun giveCommission(amount: Double) {
        grossCommission += amount
    }


}
