package com.blankwhite.expensemanager.ui.main.expenses

import com.blankwhite.expensemanager.R
import com.blankwhite.expensemanager.models.Category

enum class CategoryAttrs(val id: String, val colorId: Int, val iconId : Int) {

    PET("pet", R.color.purple_200, R.drawable.ic_pet_icon),
    SUPERMARKET("supermarket", R.color.light_hint_layout, R.drawable.ic_supermarket),
    JUNK_FOOD("junk food", R.color.black, R.drawable.ic_junk_food),
    SUBSCRIPTIONS("subscriptions", R.color.teal_200, R.drawable.ic_subscription),
    LEIZURE("leizure",R.color.white, R.drawable.ic_leizure),
    PC_GAMES("pc games", R.color.white, R.drawable.ic_pc_games_icon);

    companion object {
        fun getCategoryFromId(category: Category?) : CategoryAttrs{
            return values().find { it.id == category?.id }?:PET
        }
    }

}