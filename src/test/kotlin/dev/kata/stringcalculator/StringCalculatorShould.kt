package dev.kata.stringcalculator

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import java.util.Date

internal class StringCalculatorShould {
    @Test
    fun `Given a inventory, it should return empty list`() {
        val inventory = Inventory()
        val date = Date()

        val result = inventory.project(date)

        assertThat(result).isEmpty()
    }
}
