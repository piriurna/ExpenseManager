package com.blankwhite.expensemanager.ui.main.fragments

import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.viewbinding.ViewBinding
import com.blankwhite.expensemanager.R
import com.blankwhite.expensemanager.databinding.FragmentAddExpenseBinding
import com.blankwhite.expensemanager.models.Category
import com.blankwhite.expensemanager.ui.main.viewModels.AddExpenseViewModel
import com.blankwhite.expensemanager.ui.main.expenses.list.adapter.SpinnerAdapter
import com.blankwhite.expensemanager.utils.Status
import com.blankwhite.expensemanager.utils.currency.CurrencyTextWatcher
import com.blankwhite.expensemanager.utils.currency.OnCurrencyTextChangeListener
import dagger.hilt.android.AndroidEntryPoint
import java.math.BigDecimal
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

@AndroidEntryPoint
class AddExpenseFragment : BaseFragment(), DatePickerDialog.OnDateSetListener, OnCurrencyTextChangeListener {
    private lateinit var _binding : FragmentAddExpenseBinding
    private val binding
        get() = _binding

    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): ViewBinding {
        _binding = FragmentAddExpenseBinding.inflate(inflater, container, false)
        return _binding
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private lateinit var datePicker : DatePickerDialog

    private lateinit var adapter : SpinnerAdapter

    private lateinit var viewModel: AddExpenseViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[AddExpenseViewModel::class.java]

        return super.onCreateView(inflater, container, savedInstanceState)!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        createViews()

        setupViews()

        updateObserver()
    }


    //-----------------
    // Local Methods
    //-----------------
    private fun createViews(){
        datePicker = DatePickerDialog(requireContext())
        adapter = SpinnerAdapter(requireContext(), R.layout.category_spinner_item, mutableListOf())
    }

    private fun setupViews() {
        val currencyTextWatcher = CurrencyTextWatcher(binding.expenseValueInputEdit, Locale.getDefault(), this)
        binding.expenseValueInputEdit.addTextChangedListener(currencyTextWatcher)

        datePicker.setOnDateSetListener(this)

        binding.expenseDateInputEdit.setOnClickListener {
            datePicker.show()
        }

        binding.expenseCategoryInput.adapter = adapter

        binding.addExpenseButton.setOnClickListener {
            val name = binding.expenseNameInputEdit.text.toString()
            val category = binding.expenseCategoryInput.selectedItem as Category
            viewModel.addExpenses(name, viewModel.amount, viewModel.date, category)
        }

    }

    private fun updateObserver() {
        viewModel.fetchCategories()
        viewModel.categories.observe(viewLifecycleOwner) {
            adapter.setData(it)
        }

        viewModel.state.observe(viewLifecycleOwner) {
            when(it) {
                Status.LOADING -> {}
                Status.ERROR -> {}
                Status.SUCCESS -> {
                    Navigation.findNavController(requireActivity(), R.id.nav_host_fragment).navigate(R.id.action_addExpenseFragment_to_mainFragment)
                }
                else -> {}
            }
            viewModel.clearState()
        }
    }


    //--------------
    // Events
    //--------------
    override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
        val date = LocalDate.of(year, month + 1, day)
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        binding.expenseDateInputEdit.setText(date.format(formatter))

        viewModel.date = Date(date.toEpochDay())
    }

    override fun onCurrencyTextChanged(amount: BigDecimal) {
        viewModel.amount = amount.toDouble()

    }

}