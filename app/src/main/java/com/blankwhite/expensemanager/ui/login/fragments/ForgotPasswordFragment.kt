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
import com.blankwhite.expensemanager.ui.login.LoginRegisterActivity
import com.blankwhite.expensemanager.ui.main.fragments.BaseFragment

class ForgotPasswordFragment : BaseFragment() {

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
            val email = binding.emailEditText.text
            email?.toString()?.let { emailString ->
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
        val activity = requireActivity() as LoginRegisterActivity

        activity
            .auth
            .sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if(task.isSuccessful) {
                    val bundle = Bundle()
                    bundle.putString(EMAIL_KEY, email)
                    navigateTo(R.id.go_to_check_code, bundle)
                } else {
                    binding.emailInputLayout.error = "Incorrect Email"
                    binding.sendButton.isEnabled = true
                }
            }
    }

    companion object {
        const val EMAIL_KEY = "email"
    }
}