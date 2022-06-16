package com.blankwhite.expensemanager.ui.main.expenses.list

import android.view.View
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.blankwhite.expensemanager.R
import com.blankwhite.expensemanager.models.Expense
import com.blankwhite.expensemanager.ui.main.expenses.IconView
import com.blankwhite.expensemanager.ui.main.expenses.CategoryAttrs
import com.blankwhite.expensemanager.ui.main.expenses.list.adapter.OnExpenseClickListener
import com.blankwhite.expensemanager.utils.formatEuros


data class ExpenseItemViewHolder(
    val itemView: View,
    val listener: OnExpenseClickListener
) : RecyclerView.ViewHolder(itemView) {

//    private val expenseListItemView : ExpenseListItem = itemView as ExpenseListItem

//    var iconView : IconView? = null
//    var valueTextView : TextView? = null
//    var labelTextView : TextView? = null

    private val expenseListItemView : ExpenseListItem = itemView as ExpenseListItem

    var expense : Expense? = null
    set(expense: Expense?) {
        expenseListItemView.updateValues(expense)
        field = expense
    }
    init {
        itemView.setOnClickListener {
            expense?.let { expense ->
                listener.onExpenseClicked(expense)
            }
        }
    }

    fun getCustomView() : ExpenseListItem {
        return expenseListItemView
    }


    //SECTIONED LIST VIEW
//    fun updateValues(expense : Expense?) {
//        itemView.setOnClickListener {
//            listener.onExpenseClicked(expense!!)
//        }
//
//        expense?.let { e ->
//            iconView?.let {
//                val categoryAttrs = CategoryAttrs.getCategoryFromId(e.category!!)
//                it.setIconRes(categoryAttrs.iconId)
//            }
//            valueTextView?.let {
//                it.text = e.value.formatEuros()
//            }
//
//            labelTextView?.let {
//                it.text = e.name
//            }
//
//        }
//    }
}