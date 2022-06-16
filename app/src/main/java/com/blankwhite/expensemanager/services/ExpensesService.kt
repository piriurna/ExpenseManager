package com.blankwhite.expensemanager.services

import com.blankwhite.expensemanager.models.Category
import com.blankwhite.expensemanager.models.Expense
import com.blankwhite.expensemanager.services.handlers.ResultHandler

interface ExpensesService {

    suspend fun getAllExpenses(handler: ResultHandler<ExpensesResponse>)

    suspend fun getAllExpensesForPeriod(year: Int, month: Int, handler: ResultHandler<ExpensesResponse>)

    suspend fun getExpensesForCategory(category: Category, handler: ResultHandler<ExpensesResponse>)

    suspend fun addExpense(expense: Expense, handler: ResultHandler<ExpensesResponse>)

    suspend fun fetchCategories(handler: ResultHandler<CategoriesResponse>)
}