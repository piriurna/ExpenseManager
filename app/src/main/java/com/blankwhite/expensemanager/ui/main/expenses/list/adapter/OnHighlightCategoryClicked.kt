package com.blankwhite.expensemanager.ui.main.expenses.list.adapter

import com.blankwhite.expensemanager.models.CategoryExpense

interface OnHighlightCategoryClicked {

    fun onCategoryPressed(categoryExpense: CategoryExpense, selected: Boolean)
}