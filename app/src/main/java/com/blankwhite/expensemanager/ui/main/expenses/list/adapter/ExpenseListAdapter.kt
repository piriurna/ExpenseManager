package com.blankwhite.expensemanager.ui.main.expenses.list.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.blankwhite.expensemanager.R
import com.blankwhite.expensemanager.models.Category
import com.blankwhite.expensemanager.models.Expense
import com.blankwhite.expensemanager.ui.main.expenses.list.ExpenseItemViewHolder
import com.blankwhite.expensemanager.ui.main.expenses.list.ExpenseListItem

class ExpenseListAdapter(private val context: Context, private val listener: OnExpenseClickListener) : RecyclerView.Adapter<ExpenseItemViewHolder>(){

    private var flatData: MutableList<Expense> = mutableListOf()

    override fun getItemViewType(position: Int): Int {
        return R.layout.expense_list_item
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseItemViewHolder {
        val view = ExpenseListItem(parent.context)

        view.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        return ExpenseItemViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: ExpenseItemViewHolder, position: Int) {
        holder.expense = flatData[position]
    }

    override fun getItemCount(): Int {
        return flatData.size
    }


    fun setData(data: List<Expense>) {
        val oldFlatData = flatData
        flatData = data.toMutableList()

        if(oldFlatData != flatData){
            notifyDataSetChanged()
        }
    }

}