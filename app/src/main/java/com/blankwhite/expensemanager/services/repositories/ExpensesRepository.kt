package com.blankwhite.expensemanager.services.repositories

import com.blankwhite.expensemanager.BuildConfig
import com.blankwhite.expensemanager.models.Category
import com.blankwhite.expensemanager.models.Expense
import com.blankwhite.expensemanager.services.CategoriesResponse
import com.blankwhite.expensemanager.services.DefaultHeaders
import com.blankwhite.expensemanager.services.ExpensesResponse
import com.blankwhite.expensemanager.services.ExpensesService
import com.blankwhite.expensemanager.services.handlers.ResultHandler
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import javax.inject.Inject

class ExpensesRepository @Inject constructor(private val firebaseAuth: FirebaseAuth, private val expensesService: ExpensesService) {
    private val headers = DefaultHeaders(
        appVersion = BuildConfig.VERSION_NAME,
        uid = firebaseAuth.currentUser?.uid
    )

    suspend fun getAllExpenses(handler: ResultHandler<ExpensesResponse>) =
        expensesService.getAllExpenses(headers, handler)


    suspend fun getAllExpensesForPeriod(year: Int, month : Int, handler: ResultHandler<ExpensesResponse>) =
        expensesService.getAllExpensesForPeriod(headers, year, month, handler)

    suspend fun getExpensesForCategory(category: Category, handler: ResultHandler<ExpensesResponse>) = expensesService.getExpensesForCategory(headers, category, handler)

    suspend fun addExpense(expense: Expense, handler: ResultHandler<ExpensesResponse>) = expensesService.addExpense(headers, expense, handler)

    suspend fun fetchCategories(handler: ResultHandler<CategoriesResponse>) = expensesService.fetchCategories(headers, handler)
}