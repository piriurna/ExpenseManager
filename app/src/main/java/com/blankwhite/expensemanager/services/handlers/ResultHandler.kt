package com.blankwhite.expensemanager.services.handlers

interface ResultHandler<T> {
    fun onSuccess(result: T)
    fun onError(error: Throwable)
}