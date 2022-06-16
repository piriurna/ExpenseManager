package com.blankwhite.expensemanager.ui.main.expenses.list.adapter

import com.blankwhite.expensemanager.models.Category
import com.blankwhite.expensemanager.models.Expense

interface OnExpenseClickListener {

    fun onExpenseClicked(expense: Expense)

    fun onHeaderClicked(category: Category)
}