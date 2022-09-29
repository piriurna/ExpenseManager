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
import com.google.android.material.card.MaterialCardView

class ExpenseHeader(context: Context, attrs: AttributeSet?= null, defStyleAttr: Int = 0) : MaterialCardView(context, attrs, defStyleAttr) {

    private var dateTextView : TextView? = null
    init {
        inflate(context, R.layout.expense_header, this);

        dateTextView = findViewById(R.id.date)
    }

    fun updateValues(e : String?) {
        e?.let { e2 ->
            dateTextView?.text = e2
        }
    }
}