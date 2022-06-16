//package com.blankwhite.expensemanager.ui.main.expenses.list.adapter
//
//import android.view.View
//import android.widget.Filter
//import android.widget.Filterable
//import androidx.lifecycle.MutableLiveData
//import androidx.recyclerview.widget.RecyclerView
//import com.blankwhite.expensemanager.R
//import com.blankwhite.expensemanager.models.Expense
//import com.blankwhite.expensemanager.ui.main.expenses.list.ExpenseHeaderViewHolder
//import com.blankwhite.expensemanager.ui.main.expenses.list.ExpenseItemViewHolder
//import io.github.luizgrp.sectionedrecyclerviewadapter.Section
//import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters
//import io.github.luizgrp.sectionedrecyclerviewadapter.utils.EmptyViewHolder
//
//
//
//
//class CategorySection(items: List<Expense> = emptyList(), private val listener: OnExpenseClickListener) : Section(
//    SectionParameters.builder()
//        .itemResourceId(R.layout.expense_list_item)
//        .headerResourceId(R.layout.expense_list_header)
//        .build()
//), Filterable {
//    var filterListener : FilterableSectionListener? = null
//
//    var itemsList : MutableList<Expense> = items.toMutableList()
//
//    override fun getContentItemsTotal(): Int {
//        return itemsList.size
//    }
//
//
//    override fun getItemViewHolder(view: View?): ExpenseItemViewHolder {
//        view?.let {
//            return ExpenseItemViewHolder(view, listener)
//        }
//
//        return ExpenseItemViewHolder(view!!, listener)
//    }
//
//    override fun onBindItemViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
//        val expenseItemViewHolder = holder as ExpenseItemViewHolder
//
//        expenseItemViewHolder.updateValues(itemsList[position])
//
//    }
//
//    override fun getHeaderViewHolder(view: View?): RecyclerView.ViewHolder {
//        // return an empty instance of ViewHolder for the headers of this section
//        return ExpenseHeaderViewHolder(view!!) {}
//    }
//
//    override fun onBindHeaderViewHolder(holder: RecyclerView.ViewHolder?) {
//        val expenseHeaderViewHolder = holder as ExpenseHeaderViewHolder
//
//        expenseHeaderViewHolder.updateValues(itemsList)
//    }
//
//    override fun getFilter(): Filter {
//        return object : Filter() {
//            override fun performFiltering(charSequence: CharSequence?): FilterResults {
//                val charString = charSequence?.toString() ?: ""
//                var preFilteredList = mutableListOf<Expense>()
//
//                if (charString.isEmpty()) {
//                    preFilteredList = itemsList
//                } else {
//                    itemsList
//                        .filter { item ->
//                            return@filter item.name?.contains(charString) == true || item.category?.name?.contains(charString) == true
//                        }.forEach {
//                            preFilteredList.add(it)
//                        }
//
//
//                }
//                return FilterResults().apply { values = preFilteredList }
//            }
//
//            override fun publishResults(charSequence: CharSequence?, results: FilterResults?) {
//                val values = if(results?.values == null){
//                    mutableListOf()
//                } else {
//                    results.values as MutableList<Expense>
//                }
//
//                filterListener?.onPublishResults(values, this@CategorySection)
//            }
//        }
//    }
//}