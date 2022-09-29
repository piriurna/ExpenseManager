package com.blankwhite.expensemanager.ui.login.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.blankwhite.expensemanager.MainActivity
import com.blankwhite.expensemanager.R
import com.blankwhite.expensemanager.databinding.FragmentLoginBinding
import com.blankwhite.expensemanager.ui.common.LightStatusBar
import com.blankwhite.expensemanager.ui.common.StatusBarColor
import com.blankwhite.expensemanager.ui.main.fragments.BaseFragment
import com.blankwhite.expensemanager.utils.hideKeyboard
import com.blankwhite.expensemanager.utils.isEmail
import com.blankwhite.expensemanager.utils.onEditorEnterAction
import com.google.android.gms.tasks.Task
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginFragment : BaseFragment() {

    override fun getStatusBarColor(): StatusBarColor = LightStatusBar(requireContext())

    private lateinit var _binding : FragmentLoginBinding
    private val binding
        get() = _binding

    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): ViewBinding {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return _binding
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginButton.setOnClickListener {
            hideKeyboard()
            setButtonsEnabled(false)
            clearErrors()
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            loginWithEmail(email, password)
        }

        binding.registerButton.setOnClickListener {
            hideKeyboard()
            navigateTo(R.id.action_loginFragment_to_registerFragment)
        }

        binding.forgotYourPassword.setOnClickListener {
            hideKeyboard()
            navigateTo(R.id.action_loginFragment_to_resetPasswordScreen)
        }

        binding.passwordEditText.onEditorEnterAction {
            binding.loginButton.performClick()
        }

    }


    private fun loginWithEmail(email: String, password: String){
        if(!email.isEmail()){
            displayError(binding.emailLayout, "Invalid Email")
            return
        }

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                updateUI(task)
                if (task.isSuccessful) {
                    goToMainActivity()
                }
            }
    }

    private fun updateUI(task: Task<AuthResult>) {
        if(task.isSuccessful) {
            clearErrors()
        } else {
            setButtonsEnabled(true)
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

    private fun clearErrors() {
        binding.emailLayout.isErrorEnabled = false
        binding.emailLayout.error = ""
        binding.passwordInputLayout.error = ""
    }

    private fun goToMainActivity() {
        val mainActivityIntent = Intent(requireContext(), MainActivity::class.java)
        requireActivity().finish()
        startActivity(mainActivityIntent)

    }

    private fun setButtonsEnabled(enabled : Boolean) {
        binding.loginButton.isEnabled = enabled
        binding.googleSignIn.isEnabled = enabled
        binding.registerButton.isEnabled = enabled
//        binding.facebookSignIn.isEnabled = enabled
//        binding.githubSignIn.isEnabled = enabled
    }
}