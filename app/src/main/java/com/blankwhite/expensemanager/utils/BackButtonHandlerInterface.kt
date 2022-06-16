package com.blankwhite.expensemanager.utils

interface BackButtonHandlerInterface {
    fun addBackClickListener(onBackClickListener: OnBackClickListener)
    fun removeBackClickListener(onBackClickListener: OnBackClickListener)
}