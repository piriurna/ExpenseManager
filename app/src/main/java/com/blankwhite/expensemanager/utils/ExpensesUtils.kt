package com.blankwhite.expensemanager.utils

import android.os.Build
import androidx.annotation.RequiresApi
import com.blankwhite.expensemanager.models.Expense
import java.time.LocalDate
import java.util.*

fun List<Expense>.groupByCategory(): Map<String?, List<Expense>> {
    val sortedList = this.sortedByDescending { it.date }
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