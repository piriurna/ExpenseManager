package com.blankwhite.expensemanager.utils

import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import androidx.core.text.toSpannable
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*

fun String.toBigDecimal(locale: Locale) : BigDecimal {
    val stringToConvert = this

    val currencySymbol = NumberFormat.getCurrencyInstance(locale).currency?.symbol?:"€"

    val replaceable = String.format("[%s,.\\s]", currencySymbol)

    val cleanString = stringToConvert.replace(replaceable.toRegex(), "")

    return BigDecimal(cleanString).setScale(
        2, BigDecimal.ROUND_FLOOR
    ).divide(
        BigDecimal(100), BigDecimal.ROUND_FLOOR
    )
}


fun CharSequence.colorText(color: Int, stringToColor: String) : Spannable{
    val startIndex = this.indexOf(stringToColor)
    if(startIndex == -1) return this.toSpannable()
    val endIndex = startIndex + stringToColor.length

    val spannableText = SpannableString(this)
    spannableText.setSpan(ForegroundColorSpan(color), startIndex, endIndex, 0);
    return spannableText
}

fun CharSequence.setSpans(spans: List<Any>, stringToColor: String, flags : Int = 0) : Spannable{
    val startIndex = this.indexOf(stringToColor)
    if(startIndex == -1) return this.toSpannable()
    val endIndex = startIndex + stringToColor.length

    val spannableText = SpannableString(this)
    spans.forEach {
        spannableText.setSpan(it, startIndex, endIndex, flags);
    }
    return spannableText
}