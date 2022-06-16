package com.blankwhite.expensemanager.ui.main.expenses.list

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.blankwhite.expensemanager.R
import com.blankwhite.expensemanager.models.Expense
import com.blankwhite.expensemanager.ui.main.expenses.CategoryAttrs
import com.blankwhite.expensemanager.ui.main.expenses.IconView
import com.blankwhite.expensemanager.utils.formatEuros

class ExpenseListItem(context: Context, attrs: AttributeSet?= null, defStyleAttr: Int = 0) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var valueTextView : TextView? = null
    private var labelTextView : TextView? = null
    private var iconView : IconView? = null
    init {
        inflate(context, R.layout.expense_list_item, this);

        valueTextView = findViewById(R.id.value)
        labelTextView = findViewById(R.id.label)
        iconView = findViewById(R.id.icon)
    }

    fun updateValues(e : Expense?) {
        e?.let { expense ->
            valueTextView?.let {
                it.text = expense.value.formatEuros()
            }

            labelTextView?.let {
                it.text = expense.name
            }

            iconView?.let {
                val categoryAttrs = CategoryAttrs.getCategoryFromId(expense.category!!)
                it.setIconRes(categoryAttrs.iconId)
            }

        }
    }
}