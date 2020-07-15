package com.sopt.ounce.server

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object OunceServiceImpl {

    private const val BASE_URL = "http://52.79.90.119:3000/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val SERVICE: OunceService = retrofit.create(OunceService::class.java)
}