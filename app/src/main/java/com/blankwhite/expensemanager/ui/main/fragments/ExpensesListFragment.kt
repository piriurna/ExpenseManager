package com.blankwhite.expensemanager.ui.main.fragments

import android.os.Bundle
import android.view.*
import androidx.core.os.bundleOf
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.blankwhite.expensemanager.R
import com.blankwhite.expensemanager.databinding.FragmentMainBinding
import com.blankwhite.expensemanager.models.Category
import com.blankwhite.expensemanager.models.Expense
import com.blankwhite.expensemanager.ui.main.expenses.list.adapter.ExpenseListAdapter
import com.blankwhite.expensemanager.ui.main.expenses.list.adapter.OnExpenseClickListener
import com.blankwhite.expensemanager.ui.main.viewModels.MainViewModel
import com.blankwhite.expensemanager.utils.Status
import com.blankwhite.expensemanager.utils.visible
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ExpensesListFragment : BaseFragment(), OnExpenseClickListener {

    override fun useDefaultToolbar() : Boolean = true

    private lateinit var _binding : FragmentMainBinding
    private val binding
        get() = _binding

    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): ViewBinding {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return _binding
    }
    private lateinit var viewModel: MainViewModel

    private var adapter : ExpenseListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        return super.onCreateView(inflater, container, savedInstanceState)!!
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()

        setupViews()

    }


    //---------------
    // Local Methods
    //---------------

    private fun setupObservers(){
        viewModel.getAllExpenses()
        viewModel.expenses.observe(viewLifecycleOwner) { expenseList ->
            val sortedList = expenseList.sortedByDescending { it.date }
            adapter?.setData(sortedList)
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
            val isEmpty = adapter?.itemCount == 0
            val isError = it == Status.ERROR
            val isLoading = it == Status.LOADING

            loadingView?.visible(isLoading)
            emptyView?.visible(!isError && !isLoading && isEmpty)
            binding.recyclerView.visible(!isLoading && !isError &&!isEmpty)
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
        adapter = ExpenseListAdapter(requireContext(), this)

        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.layoutManager = layoutManager

        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                layoutManager.orientation
            )
        )

        binding.recyclerView.onFlingListener = object : RecyclerView.OnFlingListener() {
            override fun onFling(velocityX: Int, velocityY: Int): Boolean {
                val isPullingDown = velocityY < 0
                val isOnTop = layoutManager.findFirstVisibleItemPosition() == 0
                if(isPullingDown && isOnTop) {
                    viewModel.getAllExpenses()
                    return true
                }
                return false
            }
        }



        binding.recyclerView.adapter = adapter
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