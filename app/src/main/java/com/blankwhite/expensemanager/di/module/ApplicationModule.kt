package com.blankwhite.expensemanager.di.module

import com.blankwhite.expensemanager.BuildConfig
import com.blankwhite.expensemanager.services.ExpensesBaseService
import com.blankwhite.expensemanager.services.ExpensesService
import com.blankwhite.expensemanager.services.ExpensesServiceImpl
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth() : FirebaseAuth {
        return Firebase.auth
    }

    @Provides
    fun provideBaseUrl() = "https://expense-manager---2.herokuapp.com/"


    @Provides
    @Singleton
    fun provideOkHttpClient() : OkHttpClient{
        return OkHttpClient.Builder().build();
    }


    @Provides
    @Singleton
    fun provideRxAdapter() : retrofit2.CallAdapter.Factory {
        return RxJava3CallAdapterFactory.create()
    }

    @Provides
    @Singleton
    fun provideRetrofit(rxAdapter: retrofit2.CallAdapter.Factory,okHttpClient: OkHttpClient, url: String): Retrofit =
        Retrofit.Builder()
            .baseUrl(url)
            .addCallAdapterFactory(rxAdapter)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()


    @Provides
    @Singleton
    fun provideExpensesService(retrofit: Retrofit) : ExpensesBaseService {
        return retrofit.create(ExpensesBaseService::class.java)
    }

    @Provides
    @Singleton
    fun provideApiHelper(apiHelper: ExpensesServiceImpl): ExpensesService = apiHelper
}