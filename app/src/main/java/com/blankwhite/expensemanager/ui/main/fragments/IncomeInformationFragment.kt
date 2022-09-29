package com.blankwhite.expensemanager.ui.main.fragments

import com.blankwhite.expensemanager.ui.common.InformationFragment

class IncomeInformationFragment : InformationFragment() {

    override fun getTitle(): String = "Incomes"

    override fun getDescription(): String = "Soon you will be able to check all your incomes in the app"

    override fun getButtonText(): String = "Go back"

    override fun getButtonCallback(): () -> Unit {
        return {
            getNavController().popBackStack()
        }
    }

}