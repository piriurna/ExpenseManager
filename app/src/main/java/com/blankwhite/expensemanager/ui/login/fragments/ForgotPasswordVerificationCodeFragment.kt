package com.blankwhite.expensemanager.ui.login.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.blankwhite.expensemanager.databinding.ForgotPasswordVerificationCodeBinding
import com.blankwhite.expensemanager.ui.common.InformationFragment
import com.blankwhite.expensemanager.ui.login.viewmodels.ForgotPasswordVerificationCodeViewModel
import com.blankwhite.expensemanager.ui.main.fragments.BaseFragment

class ForgotPasswordVerificationCodeFragment : InformationFragment() {
    override fun getTitle(): String = "Success!"

    override fun getDescription(): String = "Follow the link that was sent to your email and proceed to reset your password"

    override fun getButtonText(): String = "Login"

    override fun getButtonCallback(): () -> Unit {
        return {
            getNavController().popBackStack()
        }
    }


}