package com.blankwhite.expensemanager.ui.main.expenses.list

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.blankwhite.expensemanager.R
import com.blankwhite.expensemanager.models.Expense
import com.blankwhite.expensemanager.ui.main.expenses.list.adapter.Header
import com.blankwhite.expensemanager.utils.formatEuros
import java.util.*


data class ExpenseHeaderViewHolder(
    override val itemView: View
) : ExpenseListBaseViewHolder(itemView) {

    private val expenseHeader : ExpenseHeader = itemView as ExpenseHeader

    var label : String = ""
    set(value) {
        expenseHeader.updateValues(value)
        field = value
    }

    fun updateValues(header : Header) {
        label = header.date!!
    }

//    fun getCustomView() : ExpenseListItem {
//        return expenseListItemView
//    }
}