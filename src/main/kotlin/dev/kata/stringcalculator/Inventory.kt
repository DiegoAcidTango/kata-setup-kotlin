package dev.kata.stringcalculator

import java.util.Date

class Inventory {
    private val products = mutableMapOf<Date, MutableList<String>>()

    fun project(date: Date) : List<String> {
        return products.toSortedMap().filterKeys {
            it <= date
        }.values.flatten()
    }

    fun add(date: Date, item: String) {
        products.computeIfAbsent(date) { mutableListOf() }.add(item)
    }
}