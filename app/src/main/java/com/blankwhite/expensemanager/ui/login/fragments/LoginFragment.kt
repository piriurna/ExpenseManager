package com.blankwhite.expensemanager.ui.login.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.blankwhite.expensemanager.MainActivity
import com.blankwhite.expensemanager.R
import com.blankwhite.expensemanager.databinding.FragmentLoginBinding
import com.blankwhite.expensemanager.ui.login.viewmodels.LoginViewModel
import com.blankwhite.expensemanager.ui.main.fragments.BaseFragment
import com.blankwhite.expensemanager.utils.hideKeyboard
import com.blankwhite.expensemanager.utils.onEditorEnterAction
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginFragment : BaseFragment() {
    private lateinit var auth: FirebaseAuth

    private lateinit var _binding : FragmentLoginBinding
    private val binding
        get() = _binding

    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): ViewBinding {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return _binding
    }

    private lateinit var viewModel : LoginViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        auth = Firebase.auth

        return super.onCreateView(inflater, container, savedInstanceState)!!
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginButton.setOnClickListener {
            hideKeyboard()
            setButtonsEnabled(false)
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
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
            if (task.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                val user = auth.currentUser
                updateUI(user)
            } else {
                updateUI(null)
            }

        }
    }


    private fun updateUI(user: FirebaseUser?) {
        if(user == null) {
            displayError()
            setButtonsEnabled(true)
        } else {
            displaySuccess()
            goToMainActivity()
        }
    }

    private fun displayError(error: String? = null) {
        // If sign in fails, display a message to the user.
        Toast.makeText(requireContext(), "Authentication failed.",
            Toast.LENGTH_SHORT).show()

        binding.passwordInputLayout.error = "Wrong password or nonexistent user"

    }

    private fun displaySuccess() {

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