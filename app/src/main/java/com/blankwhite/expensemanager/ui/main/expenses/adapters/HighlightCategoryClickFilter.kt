package com.blankwhite.expensemanager.ui.main.expenses.adapters

import androidx.recyclerview.widget.RecyclerView
import com.blankwhite.expensemanager.models.CategoryExpense
import com.blankwhite.expensemanager.ui.main.expenses.list.adapter.ExpenseListAdapter
import com.blankwhite.expensemanager.ui.main.expenses.list.adapter.OnHighlightCategoryClicked

class HighlightCategoryClickFilter(
    private val filter : MutableList<String?>,
    private val toFilterAdapter : ExpenseListAdapter
) : OnHighlightCategoryClicked {

    override fun onCategoryPressed(categoryExpense: CategoryExpense, selected: Boolean) {
        val containsId = filter.contains(categoryExpense.category.id!!)
        val containsName = filter.contains(categoryExpense.category.name!!)
        if(selected) {
            if(!containsId || !containsName) {
                filter.add(categoryExpense.category.id)
            }
        } else {
            if(containsId || containsName) {
                val idIndex = filter.indexOf(categoryExpense.category.id!!)
                if(idIndex == -1) {
                    val nameIndex = filter.indexOf(categoryExpense.category.name!!)
                    filter.removeAt(nameIndex)
                } else {
                    filter.removeAt(idIndex)
                }
            }
        }
        var stringFilter = ""
        filter.forEachIndexed { index, s ->
            if(index != 0) {
                stringFilter += ","
            }

            stringFilter += s
        }
        toFilterAdapter.filter.filter(stringFilter)
    }
}