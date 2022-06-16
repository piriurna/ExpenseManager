package com.blankwhite.expensemanager.ui.main.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.viewbinding.ViewBinding
import com.blankwhite.expensemanager.R
import com.blankwhite.expensemanager.databinding.FragmentExpenseDetailsBinding
import com.blankwhite.expensemanager.models.Expense
import com.blankwhite.expensemanager.utils.formatEuros
import com.blankwhite.expensemanager.utils.toHumanForm
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExpenseDetailsFragment : BaseFragment() {
    private var _binding :FragmentExpenseDetailsBinding? = null
    private val binding
        get() = _binding!!

    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): ViewBinding {
        _binding = FragmentExpenseDetailsBinding.inflate(inflater, container, false)

        return binding
    }
    lateinit var expense: Expense

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        arguments?.getParcelable<Expense>("expense")?.let {
            expense = it
        }?: kotlin.run {
            getNavController().popBackStack()
        }

        return super.onCreateView(inflater, container, savedInstanceState)!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }


    private fun setupViews(){
        with(binding) {
            amount.text = expense.value.formatEuros()
            title.text = expense.name
            date.text = expense.date?.toHumanForm()
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}