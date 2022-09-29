package com.blankwhite.expensemanager.ui.common

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getColor
import androidx.core.graphics.drawable.DrawableCompat
import com.blankwhite.expensemanager.R
import com.blankwhite.expensemanager.models.CategoryExpense
import com.blankwhite.expensemanager.ui.main.expenses.CategoryAttrs
import com.blankwhite.expensemanager.utils.formatEuros
import com.google.android.material.card.MaterialCardView

class CategoryHighlightCardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?= null,
    defStyleAttr: Int = 0,
    private var categoryExpense : CategoryExpense? = null
) : MaterialCardView(context, attrs, defStyleAttr) {

    private var iconView : ImageView? = null
    private var labelTextView : TextView? = null

    init {
        inflate(context, R.layout.category_highlight_card_view, this);

        labelTextView = findViewById(R.id.label)
        iconView = findViewById(R.id.icon)
        this.radius = 20F

        this.isClickable = true
        this.setOnClickListener {
            isSelected = !isSelected
        }

    }

    fun updateValues(categoryExpense: CategoryExpense) {
        labelTextView?.text = categoryExpense.amount.formatEuros()
        val drawable = ContextCompat.getDrawable(context, CategoryAttrs.getCategoryFromId(categoryExpense.category).iconId)
        iconView?.setImageDrawable(drawable)
    }

    override fun setSelected(selected: Boolean) {
        super.setSelected(selected)
        val root = findViewById<MaterialCardView>(R.id.root)
        val backgroundColor = if(selected) getColor(context, R.color.accent) else getColor(context, R.color.white)
        val iconBackgroundColor = if(selected) getColor(context, R.color.white) else getColor(context, R.color.black)

        root.setBackgroundColor(backgroundColor)
        labelTextView?.setTextColor(iconBackgroundColor)

        val wrappedDrawable = DrawableCompat.wrap(iconView?.drawable!!)
        DrawableCompat.setTint(wrappedDrawable, iconBackgroundColor)

        iconView?.setImageDrawable(wrappedDrawable)
    }
}