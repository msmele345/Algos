package com.algos.mitch.payments

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class CommissionEmployeeTest {


    val mockBankAccountValue = 500
    val mockEmployeeName = "Winnie Mele"
    val mockGrossWage = 17.00
    val subject = CommissionEmployee(
            mockEmployeeName,
            mockBankAccountValue,
            mockGrossWage
    )


    @Test
    fun `grossPayment - should return grossWage + grossCommission`() {
        subject.grossCommission = 7.0

        val expectedValue = 24.00

        val actual = subject.grossPayment()

        assertThat(actual).isEqualTo(expectedValue)

    }



}