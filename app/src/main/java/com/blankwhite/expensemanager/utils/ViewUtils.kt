package com.blankwhite.expensemanager.utils

import android.view.View
import androidx.fragment.app.Fragment

fun View.visible(condition: Boolean) {
    visibility =  if(condition) View.VISIBLE else View.GONE
}