package com.blankwhite.expensemanager.ui.main.expenses.list

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.blankwhite.expensemanager.models.Expense
import com.blankwhite.expensemanager.ui.main.expenses.list.adapter.ListItem
import com.blankwhite.expensemanager.ui.main.expenses.list.adapter.OnExpenseClickListener


data class ExpenseItemViewHolder(
    override val itemView: View,
    val listener: OnExpenseClickListener
) : ExpenseListBaseViewHolder(itemView) {

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

    fun updateValues(listItem: ListItem) {
        expense = listItem.expense
        expenseListItemView.showDivider(listItem.isAlone)
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