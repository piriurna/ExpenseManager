package com.blankwhite.expensemanager.utils

import com.blankwhite.expensemanager.models.Category

fun com.blankwhite.expensemanager.services.Category.convert() : Category {
    return Category(this.id, this.name)
}


fun Category.convertToService() : com.blankwhite.expensemanager.services.Category {
    return com.blankwhite.expensemanager.services.Category(this.name, this.id)
}