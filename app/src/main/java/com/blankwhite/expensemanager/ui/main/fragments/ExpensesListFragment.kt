package com.blankwhite.expensemanager.ui.main.fragments

import android.os.Bundle
import android.view.*
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.blankwhite.expensemanager.R
import com.blankwhite.expensemanager.databinding.FragmentMainBinding
import com.blankwhite.expensemanager.models.Category
import com.blankwhite.expensemanager.models.CategoryExpense
import com.blankwhite.expensemanager.models.Expense
import com.blankwhite.expensemanager.ui.MarginItemDecoration
import com.blankwhite.expensemanager.ui.main.expenses.list.adapter.ExpenseListAdapter
import com.blankwhite.expensemanager.ui.main.expenses.list.adapter.OnExpenseClickListener
import com.blankwhite.expensemanager.ui.main.viewModels.MainViewModel
import com.blankwhite.expensemanager.ui.common.Status
import com.blankwhite.expensemanager.ui.main.expenses.adapters.FabButtonAutoHideListener
import com.blankwhite.expensemanager.ui.main.expenses.adapters.HighlightCategoriesAdapter
import com.blankwhite.expensemanager.ui.main.expenses.adapters.HighlightCategoryClickFilter
import com.blankwhite.expensemanager.ui.main.expenses.list.adapter.OnHighlightCategoryClicked
import com.blankwhite.expensemanager.utils.groupByCategory
import com.blankwhite.expensemanager.utils.mapToModel
import com.blankwhite.expensemanager.utils.visible
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ExpensesListFragment : BaseFragment(), OnExpenseClickListener {

    override fun useDefaultToolbar() : Boolean = false

    private lateinit var _binding : FragmentMainBinding
    private val binding
        get() = _binding

    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): ViewBinding {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return _binding
    }
    private lateinit var viewModel: MainViewModel

    private var expensesListAdapter : ExpenseListAdapter? = null

    private var highlightedCategoriesListAdapter : HighlightCategoriesAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        return super.onCreateView(inflater, container, savedInstanceState)!!
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.setupWithNavController(getNavController())

        setupObservers()

        setupViews()

        setupHighlightCategoriesRecyclerView()


    }

    private var filter = mutableListOf<String?>()
    private fun setupHighlightCategoriesRecyclerView() {
        highlightedCategoriesListAdapter = HighlightCategoriesAdapter(requireContext(), HighlightCategoryClickFilter(filter, expensesListAdapter!!))

        binding.recyclerViewCategories.addItemDecoration(
            MarginItemDecoration(
                30
            )
        )
        binding.recyclerViewCategories.adapter = highlightedCategoriesListAdapter
    }


    //---------------
    // Local Methods
    //---------------

    private fun setupObservers(){
        viewModel.getAllExpenses()
        viewModel.expenses.observe(viewLifecycleOwner) { expenseList ->
            val sortedList = expenseList.sortedByDescending { it.date }
            expensesListAdapter?.setData(sortedList)

            val categoriesGroupedList = expenseList.groupByCategory()
            val mapToCategoriesExpense = categoriesGroupedList.mapToModel().sortedByDescending { it.amount }

            highlightedCategoriesListAdapter?.setData(mapToCategoriesExpense)
        }

        viewModel.state.observe(viewLifecycleOwner) {
            if(it == Status.NO_STATE) return@observe
            when(it) {
                Status.LOADING -> {viewModel.clearState()}
                Status.SUCCESS -> {viewModel.clearState()}
                Status.ERROR -> {viewModel.clearState()}
                else -> { }
            }
            val loadingView = view?.findViewById<View>(R.id.loading_screen)
            val emptyView = view?.findViewById<View>(R.id.empty_screen)
            val isEmpty = expensesListAdapter?.itemCount == 0
            val isError = it == Status.ERROR
            val isLoading = it == Status.LOADING

            loadingView?.visible(isLoading)
            emptyView?.visible(!isError && !isLoading && isEmpty)
            binding.recyclerView.visible(!isLoading && !isError &&!isEmpty)
            binding.swipeRefreshLayout.isRefreshing = it == Status.LOADING
        }
    }

    private fun setupViews() {
        setupRecyclerView()

        setupToolbar()

        binding.fab.setOnClickListener {
            navigateTo(R.id.action_mainFragment_to_addExpenseFragment)
        }
    }

    private fun setupRecyclerView() {
        expensesListAdapter = ExpenseListAdapter(requireContext(), this)

        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.getAllExpenses()
        }

        binding.recyclerView.addOnScrollListener(FabButtonAutoHideListener(binding.fab))

        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.layoutManager = layoutManager

        binding.recyclerView.adapter = expensesListAdapter
    }


    private fun setupToolbar() {

    }



    override fun onExpenseClicked(expense: Expense) {
        val bundle : Bundle = bundleOf("expense" to expense)

        navigateTo(R.id.action_enter_expense_details, bundle)
    }


    override fun onHeaderClicked(category: Category) {
//        TODO("Not yet implemented")
    }
}