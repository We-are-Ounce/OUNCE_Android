package com.sopt.ounce.server

import com.sopt.ounce.signup.api.EmailCheckService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object EmailCheckServiceImpl {

    private const val BASE_URL = "https://ounce.herokuapp.com/"

    private val retrofit : Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service : EmailCheckService = retrofit.create(EmailCheckService::class.java)
}