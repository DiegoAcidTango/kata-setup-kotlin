package dev.kata.stringcalculator

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.ZoneId
import java.util.Date

internal class StringCalculatorShould {
    @Test
    fun `Given a inventory, it should return empty list`() {
        val inventory = Inventory()
        val date = Date()

        val result = inventory.project(date)

        assertThat(result).isEmpty()
    }

    @Test
    fun `Add product to inventory`() {
        val inventory = Inventory()
        val date = Date()
        val apple = "apple"

        inventory.add(date, apple)
        val result = inventory.project(date)

        assertThat(result).isEqualTo(listOf("apple"))
    }

    @Test
    fun `Given multiple products on the same date, it should return all products`() {
        val inventory = Inventory()
        val date = Date()
        val apple = "apple"
        val banana = "banana"

        inventory.add(date, apple)
        inventory.add(date, banana)
        val result = inventory.project(date)

        assertThat(result).isEqualTo(listOf("apple", "banana"))
    }

    @Test
    fun `Given two same products on the same day, it should return two products`() {
        val inventory = Inventory()
        val date = Date()
        val apple = "apple"

        inventory.add(date, apple)
        inventory.add(date, apple)
        val result = inventory.project(date)

        assertThat(result).isEqualTo(listOf("apple", "apple"))
    }

    @Test
    fun `Given two products on different days, it should return past days products`() {
        val inventory = Inventory()
        val date1 = LocalDate.of(2021, 1, 1).atStartOfDay(ZoneId.systemDefault()).toInstant().let { Date.from(it) }
        val date2 = LocalDate.of(2021, 1, 2).atStartOfDay(ZoneId.systemDefault()).toInstant().let { Date.from(it) }
        val apple = "apple"
        val banana = "banana"

        inventory.add(date1, apple)
        inventory.add(date2, banana)
        val result1 = inventory.project(date1)
        val result2 = inventory.project(date2)

        assertThat(result1).isEqualTo(listOf("apple"))
        assertThat(result2).isEqualTo(listOf("apple", "banana"))
    }

    @Test
    fun `project inventory based on date`() {
        val inventory = Inventory()
        val today = LocalDate.of(2021, 1, 1).atStartOfDay(ZoneId.systemDefault()).toInstant().let { Date.from(it) }
        val tomorrow = LocalDate.of(2021, 1, 2).atStartOfDay(ZoneId.systemDefault()).toInstant().let { Date.from(it) }
        val yesterday = LocalDate.of(2019, 1, 2).atStartOfDay(ZoneId.systemDefault()).toInstant().let { Date.from(it) }
        val orange = "orange"
        val banana = "banana"
        val kiwi = "kiwi"

        inventory.add(today, banana)
        inventory.add(today, kiwi)
        inventory.add(tomorrow, orange)

        val result1 = inventory.project(today)
        val result2 = inventory.project(tomorrow)
        val result3 = inventory.project(yesterday)

        assertThat(result3).isEqualTo(emptyList<String>())
        assertThat(result1).isEqualTo(listOf("banana", "kiwi"))
        assertThat(result2).isEqualTo(listOf("banana", "kiwi", "orange"))
    }
}
