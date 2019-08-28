package com.algos.mitch.payments

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class SalaryEmployeeTest {


    val mockBankAccountValue = 500
    val mockEmployeeName = "Winnie Mele"
    val mockGrossWage = 17.00
    val subject = SalaryEmployee(
            mockEmployeeName,
            mockBankAccountValue,
            mockGrossWage
    )


    @Test
    fun `grossPayment - should return value of grossWage`() {
        val expectedValue = 17.00

        val actual = subject.grossPayment()

        assertThat(actual).isEqualTo(expectedValue)

    }

}