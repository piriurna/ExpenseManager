package com.blankwhite.expensemanager.ui.main.expenses.list

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.blankwhite.expensemanager.R
import com.blankwhite.expensemanager.models.Expense
import com.blankwhite.expensemanager.ui.main.expenses.CategoryAttrs
import com.blankwhite.expensemanager.ui.main.expenses.IconView
import com.blankwhite.expensemanager.utils.formatEuros
import com.google.android.material.card.MaterialCardView
import com.google.android.material.divider.MaterialDivider

class ExpenseListItem(context: Context, attrs: AttributeSet?= null, defStyleAttr: Int = 0) : MaterialCardView(context, attrs, defStyleAttr) {

    private var valueTextView : TextView
    private var labelTextView : TextView
    private var iconView : IconView
    private var dividerView : MaterialDivider

    init {
        inflate(context, R.layout.expense_list_item, this);

        valueTextView = findViewById(R.id.value)
        labelTextView = findViewById(R.id.label)
        iconView = findViewById(R.id.icon)
        dividerView = findViewById(R.id.divider)

        isClickable = true
    }

    fun updateValues(e : Expense?) {
        e?.let { expense ->
            valueTextView.text = expense.value.formatEuros()

            labelTextView.text = expense.name

            val categoryAttrs = CategoryAttrs.getCategoryFromId(expense.category!!)
            iconView.setIconRes(categoryAttrs.iconId)
        }
    }

    fun showDivider(show : Boolean) {
        dividerView.isVisible = !show
    }
}