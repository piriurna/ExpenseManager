package com.blankwhite.expensemanager.ui

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.TextView
import com.blankwhite.expensemanager.R
import com.google.android.material.card.MaterialCardView

class MenuItemCard @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : MaterialCardView(context, attrs, defStyleAttr) {

    private var labelTextView : TextView? = null
    private var iconView : ImageView? = null


    init {
        inflate(context, R.layout.menu_item_card, this);

        labelTextView = findViewById(R.id.label)
        iconView = findViewById(R.id.icon)


    }

}