package com.blankwhite.expensemanager.ui.main.expenses.list.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.blankwhite.expensemanager.R
import com.blankwhite.expensemanager.models.Category
import com.blankwhite.expensemanager.ui.main.expenses.list.CategoryViewHolder

class SpinnerAdapter(context: Context, resource: Int = 0, val categories : MutableList<Category>) : ArrayAdapter<Category>(context, resource, categories) {


    override fun getCount(): Int {
        return categories.size
    }

    fun setData(categories: List<Category>) {
        this.categories.addAll(categories)
        notifyDataSetChanged()
    }
}