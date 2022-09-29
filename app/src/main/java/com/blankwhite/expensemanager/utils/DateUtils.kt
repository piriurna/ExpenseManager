package com.blankwhite.expensemanager.utils

import android.os.Build
import java.time.Duration
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

fun Date.toHumanForm() : String{
     if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
         val localDate = LocalDate.ofEpochDay(Duration.ofMillis(time).toDays())
         val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy", )
         return localDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL))
    } else {
        TODO("VERSION.SDK_INT < O")
    }



}