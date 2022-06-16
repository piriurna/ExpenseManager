package com.blankwhite.expensemanager.services.repositories

import com.blankwhite.expensemanager.models.Category
import com.blankwhite.expensemanager.models.Expense
import com.blankwhite.expensemanager.services.CategoriesResponse
import com.blankwhite.expensemanager.services.ExpensesResponse
import com.blankwhite.expensemanager.services.ExpensesService
import com.blankwhite.expensemanager.services.handlers.ResultHandler
import javax.inject.Inject

class ExpensesRepository @Inject constructor(private val expensesService: ExpensesService) {

    suspend fun getAllExpenses(handler: ResultHandler<ExpensesResponse>) = expensesService.getAllExpenses(handler)

    suspend fun getAllExpensesForPeriod(year: Int, month : Int, handler: ResultHandler<ExpensesResponse>) = expensesService.getAllExpensesForPeriod(year, month, handler)

    suspend fun getExpensesForCategory(category: Category, handler: ResultHandler<ExpensesResponse>) = expensesService.getExpensesForCategory(category, handler)

    suspend fun addExpense(expense: Expense, handler: ResultHandler<ExpensesResponse>) = expensesService.addExpense(expense, handler)

    suspend fun fetchCategories(handler: ResultHandler<CategoriesResponse>) = expensesService.fetchCategories(handler)
}