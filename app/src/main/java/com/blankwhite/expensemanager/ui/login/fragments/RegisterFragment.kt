package com.blankwhite.expensemanager.ui.login.fragments

import android.graphics.Typeface
import android.os.Bundle
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getColor
import androidx.viewbinding.ViewBinding
import com.blankwhite.expensemanager.R
import com.blankwhite.expensemanager.databinding.RegisterFragmentBinding
import com.blankwhite.expensemanager.ui.common.LightStatusBar
import com.blankwhite.expensemanager.ui.common.StatusBarColor
import com.blankwhite.expensemanager.ui.main.fragments.BaseFragment
import com.blankwhite.expensemanager.utils.*
import com.google.android.gms.tasks.Task
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException

class RegisterFragment : BaseFragment(){

    override fun getStatusBarColor(): StatusBarColor = LightStatusBar(requireContext())

    private lateinit var _binding : RegisterFragmentBinding
    private val binding
    get() = _binding

    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): ViewBinding {
        _binding = RegisterFragmentBinding.inflate(inflater, container, false)
        return _binding
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupButtons()

        setTexts()

        setupToolbar()
    }

    private fun setupButtons() {
        binding.sendButton.setOnClickListener {
            binding.sendButton.isEnabled = false
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            registerWithEmail(email, password)
        }

        binding.passwordEditText.onEditorEnterAction {
            hideKeyboard()
        }
    }

    private fun registerWithEmail(email: String, password: String) {
        if(!email.isEmail()) {
            displayError(binding.emailInputLayout, "Invalid Email")
            return
        }

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                updateUI(task)
                if(task.isSuccessful) {
                    getMainActivity().refreshUser()
                }
            }
    }

    private fun updateUI(task: Task<AuthResult>) {
        if(task.isSuccessful) {
            clearErrors()
        } else {
            binding.sendButton.isEnabled = true
            when {
                task.isCanceled -> {
                    displayError("Login Canceled")
                }

                task.exception is FirebaseAuthInvalidCredentialsException -> {
                    displayError(task.exception?.message)
                }
            }
        }
    }

    private fun displayError(error: String?) {
        displayError(binding.passwordInputLayout, error)
    }

    private fun displayError(textField: TextInputLayout, error: String?) {
        textField.error = error
    }

    private fun setTexts() {
        val colorSpan = ForegroundColorSpan(getColor(requireContext(), R.color.primary))
        val boldSpan = StyleSpan(Typeface.BOLD);
        val spanList = listOf(colorSpan, boldSpan) as List<Any>
        binding.checkTerms.text = binding.checkTerms.text.setSpans(spanList, "terms of condition");
    }

    private fun setupToolbar() {
        binding.toolbar.setNavigationOnClickListener {
            getNavController().popBackStack()
        }
    }

    private fun clearErrors() {
        binding.emailInputLayout.isErrorEnabled = false
        binding.emailInputLayout.error = ""
        binding.passwordInputLayout.error = ""
    }

}