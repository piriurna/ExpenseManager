package com.blankwhite.expensemanager.utils

import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*

fun Double.formatEuros(locale: Locale = Locale.getDefault()) : String =
    String.format("%.2f", this).formatEuros(locale)

fun String.formatEuros(locale: Locale = Locale.getDefault()) : String {

    val amount: BigDecimal = this.toBigDecimal(locale)
    return NumberFormat.getCurrencyInstance(locale).format(amount)
}

