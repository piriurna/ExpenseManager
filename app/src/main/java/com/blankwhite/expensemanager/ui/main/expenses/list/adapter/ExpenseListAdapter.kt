package com.blankwhite.expensemanager.ui.main.expenses.list.adapter

import android.content.Context
import android.os.Build
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.blankwhite.expensemanager.R
import com.blankwhite.expensemanager.models.Category
import com.blankwhite.expensemanager.models.Expense
import com.blankwhite.expensemanager.ui.main.expenses.list.*
import com.blankwhite.expensemanager.utils.groupByDate
import com.blankwhite.expensemanager.utils.listWithHeaders

class ExpenseListAdapter(
    private val context: Context,
    private val listener: OnExpenseClickListener
    ) : RecyclerView.Adapter<ExpenseListBaseViewHolder>(), Filterable{

    private var flatData: MutableList<Expense> = mutableListOf()
    set(value) {
        val grouped = value.groupByDate()
        itemsWithHeaders = grouped.listWithHeaders()
        field = value
    }

    private var itemsWithHeaders : List<Item>? = null

    private var filteredItems : List<Item> = listOf()

    override fun getItemViewType(position: Int): Int {
        return when {
            filteredItems[position] is Header -> {
                R.layout.expense_header
            }

            itemsWithHeaders?.get(position) is ListItem -> {
                R.layout.expense_list_item
            }

            else -> {
                R.layout.expense_list_item
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseListBaseViewHolder {
        if(viewType == R.layout.expense_list_item) {
            val view = ExpenseListItem(parent.context)

            view.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )

            return ExpenseItemViewHolder(view, listener)
        } else {
            val view = ExpenseHeader(parent.context)

            view.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )

            return ExpenseHeaderViewHolder(view)
        }

    }

    override fun onBindViewHolder(holder: ExpenseListBaseViewHolder, position: Int) {
        if(filteredItems.get(position) is ListItem) {
            (holder as ExpenseItemViewHolder).updateValues(filteredItems[position] as ListItem)
        } else {
            (holder as ExpenseHeaderViewHolder).updateValues(filteredItems[position] as Header)
        }
    }

    override fun getItemCount(): Int {
        return filteredItems.size ?:0
    }


    fun setData(data: List<Expense>) {
        val oldFlatData = flatData
        flatData = data.toMutableList()

        if(oldFlatData != flatData){
            notifyDataSetChanged()
            filteredItems = itemsWithHeaders!!
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(filter: CharSequence?): FilterResults {
                val filters = filter?.split(",")
                val filterResults = FilterResults()
                val filteredList = mutableListOf<Expense>()

                if(filters.isNullOrEmpty() || flatData.isNullOrEmpty()) {
                    filteredList.addAll(flatData)
                    val groupedList = filteredList.groupByDate().listWithHeaders()
                    filterResults.values = groupedList
                    return filterResults
                }

                filters.forEach { filterString ->
                    flatData.forEach { item ->
                        val hasIdFilter = item.category?.id!!.contains(filterString)
                        val hasNameFilter = item.category.name!!.contains(filterString)
                        if(hasIdFilter || hasNameFilter) {
                            filteredList.add(item)
                        }
                    }
                }

                val groupedList = filteredList.groupByDate().listWithHeaders()
                filterResults.values = groupedList
                return filterResults
            }

            override fun publishResults(filter: CharSequence?, results: FilterResults?) {
                filteredItems = results?.values as List<Item>
                notifyDataSetChanged()
            }

        }
    }

}


open class Item () {

}

class Header(var date : String? = null) : Item() {

}

class ListItem(
    var expense : Expense? = null,
    var isAlone : Boolean = false
) : Item() {

}