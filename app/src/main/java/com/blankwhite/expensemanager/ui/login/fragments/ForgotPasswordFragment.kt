package com.blankwhite.expensemanager.ui.login.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.viewbinding.ViewBinding
import com.blankwhite.expensemanager.R
import com.blankwhite.expensemanager.databinding.ForgotPasswordScreenBinding
import com.blankwhite.expensemanager.ui.common.LightStatusBar
import com.blankwhite.expensemanager.ui.common.StatusBarColor
import com.blankwhite.expensemanager.ui.login.LoginRegisterActivity
import com.blankwhite.expensemanager.ui.main.fragments.BaseFragment
import com.blankwhite.expensemanager.utils.isEmail

class ForgotPasswordFragment : BaseFragment() {

    override fun getStatusBarColor(): StatusBarColor = LightStatusBar(requireContext())

    private lateinit var _binding : ForgotPasswordScreenBinding
    private val binding
        get() = _binding

    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): ViewBinding {
        _binding = ForgotPasswordScreenBinding.inflate(inflater, container, false)
        return _binding
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupButton()

        setupToolbar()
    }

    private fun setupButton() {
        binding.sendButton.setOnClickListener {
            it.isEnabled = false
            val email = binding.emailEditText.text?.toString()
            email?.let { emailString ->
                sendCodeToEmail(emailString)
            }
        }
    }

    private fun setupToolbar() {
        binding.toolbar.setNavigationOnClickListener {
            getNavController().popBackStack()
        }
    }

    private fun sendCodeToEmail(email : String) {
        if(!email.isEmail()){
            binding.sendButton.isEnabled = true
            binding.emailInputLayout.error = "Invalid Email"
            return
        }

        auth
            .sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if(task.isSuccessful) {
                    clearErrors()
                    val bundle = Bundle()
                    bundle.putString(EMAIL_KEY, email)
                    navigateTo(R.id.go_to_check_code, bundle)
                } else {
                    binding.sendButton.isEnabled = true
                    binding.emailInputLayout.error = task.exception?.message
                    binding.sendButton.isEnabled = true
                }
            }
    }

    private fun clearErrors() {
        binding.emailInputLayout.error = ""
    }

    companion object {
        const val EMAIL_KEY = "email"
    }
}