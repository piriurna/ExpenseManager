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
import com.blankwhite.expensemanager.utils.colorText
import com.blankwhite.expensemanager.utils.hideKeyboard
import com.blankwhite.expensemanager.utils.onEditorEnterAction
import com.blankwhite.expensemanager.utils.setSpans

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
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            getMainActivity()
                .auth
                .createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if(task.isSuccessful) {
                        getMainActivity().refreshUser()
                    }
                }
        }

        binding.passwordEditText.onEditorEnterAction {
            hideKeyboard()
        }
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

}