package com.sopt.ounce.searchmain.network.recommendcat

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RequestRecommendCatToServer {
    var retrofit = Retrofit.Builder()
        .baseUrl("https://52.79.90.119:3000/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var service : RequestRecoomendCatInterface = retrofit.create(RequestRecoomendCatInterface::class.java)


}