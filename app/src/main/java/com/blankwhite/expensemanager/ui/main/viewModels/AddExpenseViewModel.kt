package com.blankwhite.expensemanager.ui.main.viewModels

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blankwhite.expensemanager.models.Category
import com.blankwhite.expensemanager.models.Expense
import com.blankwhite.expensemanager.services.CategoriesResponse
import com.blankwhite.expensemanager.services.ExpensesResponse
import com.blankwhite.expensemanager.services.NetworkHelper
import com.blankwhite.expensemanager.services.handlers.ResultHandler
import com.blankwhite.expensemanager.services.repositories.ExpensesRepository
import com.blankwhite.expensemanager.utils.Status
import com.blankwhite.expensemanager.utils.convert
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.*
import javax.inject.Inject

@HiltViewModel
class AddExpenseViewModel @Inject constructor(
    private val expensesRepository: ExpensesRepository,
    private val networkHelper: NetworkHelper
): ViewModel() {

    private val _categories = MutableLiveData<List<Category>>()
    val categories : LiveData<List<Category>> = _categories

    var amount : Double = 0.0

    var date : Date = Calendar.getInstance().time

    private val _state = MutableLiveData<Status>()
    val state : LiveData<Status> = _state

    fun clearState() {
        _state.postValue(Status.NO_STATE)
    }

    fun fetchCategories() {
        viewModelScope.launch {
            expensesRepository.fetchCategories(object : ResultHandler<CategoriesResponse> {
                override fun onSuccess(result: CategoriesResponse) {
                    val categoriesFormatted = result.categories.map { it.convert() }
                    _categories.postValue(categoriesFormatted)
                }

                override fun onError(error: Throwable) {
                    _categories.postValue(emptyList())
                }

            })
        }
    }

    fun addExpenses(name: String, amount: Double, date: Date, category: Category) {
        _state.postValue(Status.LOADING)
        val newExpense = Expense(name, Date(date.time), category, amount)

        viewModelScope.launch {
            expensesRepository.addExpense(newExpense, object : ResultHandler<ExpensesResponse> {
                override fun onSuccess(result: ExpensesResponse) {
                    _state.postValue(Status.SUCCESS)
                }

                override fun onError(error: Throwable) {
                    _state.postValue(Status.ERROR)
                }

            })
        }
    }
}