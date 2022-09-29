package com.blankwhite.expensemanager.ui.common

import android.content.Context
import androidx.core.content.ContextCompat.getColor
import com.blankwhite.expensemanager.R

sealed class StatusBarColor(
    val color : Int,
    val isLight : Boolean
)


class LightStatusBar(context: Context) : StatusBarColor(getColor(context, R.color.white), true)

class AppStatusBar(context: Context) : StatusBarColor(getColor(context, R.color.primary), false)