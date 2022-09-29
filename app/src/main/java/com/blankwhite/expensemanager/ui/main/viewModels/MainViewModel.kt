package com.blankwhite.expensemanager.ui.main.viewModels

import android.os.Build
import android.os.Handler
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blankwhite.expensemanager.models.Expense
import com.blankwhite.expensemanager.services.ExpensesResponse
import com.blankwhite.expensemanager.services.handlers.ResultHandler
import com.blankwhite.expensemanager.services.repositories.ExpensesRepository
import com.blankwhite.expensemanager.ui.common.Status
import com.blankwhite.expensemanager.utils.convert
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val expensesRepository: ExpensesRepository
    ): ViewModel() {

    private var _expenses = MutableLiveData<List<Expense>>()
    val expenses : LiveData<List<Expense>> = _expenses

    private val _state = MutableLiveData<Status>()
    val state : LiveData<Status> = _state


    fun clearState() {
        _state.postValue(Status.NO_STATE)
    }

    fun getAllExpenses() {
        _state.postValue(Status.LOADING)

        //TODO: Improve this with cache
        if(!expenses.value.isNullOrEmpty()){
            Handler().postDelayed({
                _expenses.postValue(expenses.value)
                _state.postValue(Status.SUCCESS)
                return@postDelayed
            }, 1500)
        }else {
            viewModelScope.launch {
                expensesRepository.getAllExpenses(object : ResultHandler<ExpensesResponse> {
                    @RequiresApi(Build.VERSION_CODES.O)
                    override fun onSuccess(result: ExpensesResponse) {
                        val expensesList = result.expenses
                        _expenses.postValue(expensesList.convert())
                        _state.postValue(Status.SUCCESS)
                    }

                    override fun onError(error: Throwable) {
                        _state.postValue(Status.ERROR)
                    }

                })
            }
        }


    }

}