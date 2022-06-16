package com.blankwhite.expensemanager.services

import com.google.gson.annotations.SerializedName
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import java.sql.Date
import java.sql.Timestamp

interface ExpensesBaseService {

    @GET("expenses/listAll")
    fun getAllExpenses(): Single<ExpensesResponse>


    @GET("expenses/listAll/{year}/{month}")
    fun getAllExpensesForPeriod(
        @Path("year") year : Int,
        @Path("month") month : Int,
    ): Single<ExpensesResponse>


    @GET("expenses/listCategory/{category}")
    fun getExpensesForCategory(
        @Path("category") category : String?
    ) : Single<ExpensesResponse>


    @POST("expenses/add")
    fun addExpense(
        @Body body: ExpenseAddBody
    ) : Single<ExpensesResponse>

    @GET("categories/all")
    fun fetchCategories() : Single<CategoriesResponse>
}


data class ExpenseAddBody(
    @SerializedName("expense") val category: Expense?
)

data class Expense(
    @SerializedName("name") val name : String?,
    @SerializedName("date") val date : Long?,
    @SerializedName("category") val category: Category?,
    @SerializedName("value") val value : Double
)

data class Category(
    @SerializedName("name") val name : String?,
    @SerializedName("id") val id : String?,
)

data class ExpensesResponse(
    @SerializedName("expenses") val expenses : List<Expense>
)

data class AddExpenseReponse(
    @SerializedName("sentExpense") val expense : Expense
)
data class CategoriesResponse(
    @SerializedName("categories") val categories: List<Category>
)