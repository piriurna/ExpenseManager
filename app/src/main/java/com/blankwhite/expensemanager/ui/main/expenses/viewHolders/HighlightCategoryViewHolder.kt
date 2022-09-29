package com.blankwhite.expensemanager.ui.main.expenses.viewHolders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.blankwhite.expensemanager.models.CategoryExpense
import com.blankwhite.expensemanager.ui.common.CategoryHighlightCardView
import com.blankwhite.expensemanager.ui.main.expenses.list.adapter.OnExpenseClickListener
import com.blankwhite.expensemanager.ui.main.expenses.list.adapter.OnHighlightCategoryClicked

class HighlightCategoryViewHolder(
    val itemView: View,
    val listener: OnHighlightCategoryClicked
) : RecyclerView.ViewHolder(itemView) {

    private val categoryExpenseListItem : CategoryHighlightCardView = itemView as CategoryHighlightCardView


    var category : CategoryExpense? = null
    set(value) {
        value?.let {
            categoryExpenseListItem.updateValues(it)
        }

        categoryExpenseListItem.setOnClickListener {
            it.isSelected = !it.isSelected
            listener.onCategoryPressed(value!!, it.isSelected)
        }
        field = value
    }

}