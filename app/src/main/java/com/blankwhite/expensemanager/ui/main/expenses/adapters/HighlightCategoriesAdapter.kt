package com.blankwhite.expensemanager.ui.main.expenses.adapters

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.blankwhite.expensemanager.R
import com.blankwhite.expensemanager.models.CategoryExpense
import com.blankwhite.expensemanager.models.Expense
import com.blankwhite.expensemanager.ui.common.CategoryHighlightCardView
import com.blankwhite.expensemanager.ui.main.expenses.list.adapter.OnExpenseClickListener
import com.blankwhite.expensemanager.ui.main.expenses.list.adapter.OnHighlightCategoryClicked
import com.blankwhite.expensemanager.ui.main.expenses.viewHolders.HighlightCategoryViewHolder

class HighlightCategoriesAdapter(private val context: Context, private val listener: OnHighlightCategoryClicked) : RecyclerView.Adapter<HighlightCategoryViewHolder>() {
    private var flatData: MutableList<CategoryExpense> = mutableListOf()


    override fun getItemViewType(position: Int): Int {
        return R.layout.category_highlight_card_view
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HighlightCategoryViewHolder {
        val view = CategoryHighlightCardView(parent.context)

        view.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )

        return HighlightCategoryViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: HighlightCategoryViewHolder, position: Int) {
        holder.category = flatData[position]
    }

    override fun getItemCount(): Int = flatData.size

    fun setData(data: List<CategoryExpense>) {
        val oldFlatData = flatData
        flatData = data.toMutableList()

        if(oldFlatData != flatData){
            notifyDataSetChanged()
        }
    }

}