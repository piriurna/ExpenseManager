package com.blankwhite.expensemanager.utils.currency

import java.math.BigDecimal

interface OnCurrencyTextChangeListener {

    fun onCurrencyTextChanged(amount: BigDecimal)
}