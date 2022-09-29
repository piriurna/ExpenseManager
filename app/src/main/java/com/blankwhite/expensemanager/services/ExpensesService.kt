package com.blankwhite.expensemanager.services

import com.blankwhite.expensemanager.models.Category
import com.blankwhite.expensemanager.models.Expense
import com.blankwhite.expensemanager.services.handlers.ResultHandler

interface ExpensesService {

    suspend fun getAllExpenses(headers: DefaultHeaders, handler: ResultHandler<ExpensesResponse>)

    suspend fun getAllExpensesForPeriod(headers: DefaultHeaders, year: Int, month: Int, handler: ResultHandler<ExpensesResponse>)

    suspend fun getExpensesForCategory(headers: DefaultHeaders,category: Category, handler: ResultHandler<ExpensesResponse>)

    suspend fun addExpense(headers: DefaultHeaders, expense: Expense, handler: ResultHandler<ExpensesResponse>)

    suspend fun fetchCategories(headers: DefaultHeaders, handler: ResultHandler<CategoriesResponse>)
}