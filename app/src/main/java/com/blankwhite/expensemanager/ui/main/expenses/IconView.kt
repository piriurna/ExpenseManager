package com.blankwhite.expensemanager.ui.main.expenses

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.blankwhite.expensemanager.R

class IconView @JvmOverloads constructor(context: Context, attrs: AttributeSet?= null, defStyleAttr: Int = 0) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var backgroundView : ImageView? = null

    private var iconView : ImageView? = null

    private var iconID : Int
    init {
        inflate(context, R.layout.icon_view, this);

        backgroundView = findViewById(R.id.background)
        iconView = findViewById(R.id.icon_res)


        val iconRes = getContext().obtainStyledAttributes(attrs, R.styleable.IconView, defStyleAttr,0)
        iconRes.apply {
            try {
                iconID = getResourceId(R.styleable.IconView_iconRes, 0)
                if(iconID != 0) {
                    iconView?.setImageDrawable(ContextCompat.getDrawable(context, iconID))
                }
            } finally {
                recycle()
            }
        }
    }

    fun setIconRes(icon: Int) {
        iconID = icon
        iconView?.setImageDrawable(ContextCompat.getDrawable(context, icon))
    }

}