package com.blankwhite.expensemanager.services

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitInstance {

    companion object{
        fun <T> createService(url: String, serviceClass: Class<T>): T {

            val client = OkHttpClient.Builder()

            val rxAdapter = RxJava3CallAdapterFactory.create()

            return Retrofit.Builder()
                .baseUrl(url)
                .addCallAdapterFactory(rxAdapter)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())
                .build()
                .create(serviceClass)
        }
    }
}