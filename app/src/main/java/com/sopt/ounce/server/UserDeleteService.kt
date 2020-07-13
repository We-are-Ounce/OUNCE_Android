package com.sopt.ounce.server

import com.sopt.ounce.login.api.DeleteService
import com.sopt.ounce.login.api.ModifyService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object UserDeleteService {
    private val interceptor = Interceptor { chain ->
        val header = "application/json"
        val newRequest = chain.request()
            .newBuilder()
            .addHeader("Content-Type",header)
            .build()

        chain.proceed(newRequest)
    }

    private val client = OkHttpClient.Builder().apply {
        interceptors().add(interceptor)
    }.build()

    private const val BASE_URL = "http://52.79.90.119:3000/"

    private val retrofit : Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    val service : DeleteService = retrofit.create(DeleteService::class.java)
}