package com.blankwhite.expensemanager.ui.main.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getColor
import androidx.viewbinding.ViewBinding
import com.blankwhite.expensemanager.R
import com.blankwhite.expensemanager.databinding.HomeFragmentBinding

class HomeFragment : BaseFragment() {

    private lateinit var _binding : HomeFragmentBinding
    private val binding
    get() = _binding

    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): ViewBinding {
        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        return _binding
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.expensesButton.setOnClickListener {
            navigateTo(R.id.action_homeFragment_to_mainFragment)
        }

        binding.incomeButton.setOnClickListener {
            navigateTo(R.id.action_homeFragment_to_incomeInformationFragment)
        }
    }

}