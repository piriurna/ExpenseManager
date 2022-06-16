package com.blankwhite.expensemanager.ui.main.expenses.list

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.blankwhite.expensemanager.R
import com.blankwhite.expensemanager.models.Expense
import com.blankwhite.expensemanager.utils.formatEuros
import java.util.*


data class ExpenseHeaderViewHolder(
    val itemView: View,
    val onClickListener: View.OnClickListener
) : RecyclerView.ViewHolder(itemView) {

//    private val expenseListItemView : ExpenseListItem = itemView as ExpenseListItem

    var amountTextView : TextView? = null
    var labelTextView : TextView? = null

    init {
        itemView.setOnClickListener(onClickListener)

        amountTextView = itemView.findViewById(R.id.amount)
        labelTextView = itemView.findViewById(R.id.label)
    }

    fun updateValues(expenses : List<Expense>?) {
        expenses?.let { list ->
            val amountLists = list.map { it.value }
            amountTextView?.let {
                it.text = amountLists.sum().formatEuros()
            }

            labelTextView?.let {
                it.text = list.first().category?.id?.capitalize(Locale.ROOT)
            }

        }
    }

//    fun getCustomView() : ExpenseListItem {
//        return expenseListItemView
//    }
}