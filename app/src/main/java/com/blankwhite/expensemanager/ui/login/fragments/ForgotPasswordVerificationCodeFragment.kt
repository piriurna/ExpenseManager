package com.blankwhite.expensemanager.ui.login.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.blankwhite.expensemanager.ui.login.viewmodels.ForgotPasswordVerificationCodeViewModel
import com.blankwhite.expensemanager.R
import com.blankwhite.expensemanager.databinding.ForgotPasswordVerificationCodeBinding
import com.blankwhite.expensemanager.ui.main.fragments.BaseFragment

class ForgotPasswordVerificationCodeFragment : BaseFragment() {

    private var _binding : ForgotPasswordVerificationCodeBinding? = null
    private val binding : ForgotPasswordVerificationCodeBinding
    get() = _binding!!
    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): ViewBinding {
        _binding = ForgotPasswordVerificationCodeBinding.inflate(inflater, container, false)
        return binding
    }

    private lateinit var viewModel: ForgotPasswordVerificationCodeViewModel

    private lateinit var email : String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this)[ForgotPasswordVerificationCodeViewModel::class.java]
        return super.onCreateView(inflater, container, savedInstanceState)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getString(ForgotPasswordFragment.EMAIL_KEY)?.let {
            email = it
        }?: kotlin.run {
            //display error and go back
        }

        binding.sendButton.setOnClickListener {
            checkCode()
        }
    }


    private fun checkCode() {

    }
}