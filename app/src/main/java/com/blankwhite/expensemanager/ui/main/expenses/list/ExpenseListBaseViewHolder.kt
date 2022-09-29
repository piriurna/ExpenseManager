package com.blankwhite.expensemanager.ui.main.expenses.list

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.blankwhite.expensemanager.models.Expense
import com.blankwhite.expensemanager.ui.main.expenses.list.adapter.OnExpenseClickListener


open class ExpenseListBaseViewHolder(
    open val itemView: View,
) : RecyclerView.ViewHolder(itemView) {



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