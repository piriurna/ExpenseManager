package com.blankwhite.expensemanager.utils

import com.blankwhite.expensemanager.models.CategoryExpense
import com.blankwhite.expensemanager.models.Expense
import com.blankwhite.expensemanager.ui.main.expenses.list.adapter.Header
import com.blankwhite.expensemanager.ui.main.expenses.list.adapter.Item
import com.blankwhite.expensemanager.ui.main.expenses.list.adapter.ListItem
import java.util.*
import kotlin.collections.HashMap

fun List<Expense>.groupByCategory(): Map<String?, List<Expense>> {
    val sortedList = this.sortedByDescending { it.value }
    return sortedList.groupBy { it.category?.id }
}

fun List<com.blankwhite.expensemanager.services.Expense>.convert() : List<Expense>{
    return this.map { it.convert() }
}

fun com.blankwhite.expensemanager.services.Expense.convert() : Expense {
    return Expense(this.name, Date(this.date!!), this.category?.convert(), this.value)
}

fun Expense.convertToService() : com.blankwhite.expensemanager.services.Expense {
    return com.blankwhite.expensemanager.services.Expense(this.name, this.date!!.time, this.category!!.convertToService(), this.value)
}


fun Map<String?, List<Expense>>.mapToModel() : List<CategoryExpense>{
    return this.values.map { list ->
        return@map CategoryExpense(
            list.first().category!!,
            list.sumOf { it.value }
        )
    }
}

fun List<Expense>.groupByDate() : Map<String?, MutableList<Expense>?> {
    val hashMap = HashMap<String?, MutableList<Expense>?>()
    val sortedList = this.sortedByDescending { it.date }
    sortedList.forEach { expense ->
        val date = expense.date
        val list = hashMap[date?.toHumanForm()]
        list?.let {
            it.add(expense)
        }?: kotlin.run {
            hashMap[date?.toHumanForm()] = mutableListOf(expense)
        }
    }

    return hashMap
}

fun Map<String?, MutableList<Expense>?>.listWithHeaders() : List<Item> {
    val finalList =mutableListOf<Item>()
    this.forEach { (s, mutableList) ->
        finalList.add(Header(s))
        val flatList = mutableList!!.map {
            val index = mutableList.indexOf(it)
            return@map ListItem(it, index >= mutableList.size - 1)
        }
        finalList.addAll(flatList)
    }

    return finalList
}