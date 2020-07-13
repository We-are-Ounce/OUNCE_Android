package com.sopt.ounce.login.api

import com.sopt.ounce.login.data.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET

interface DetailService {
    @GET("review/detail/:reviewIdx")
    fun putModifyAdd(
        @Body body : RequestDetailData
    ) : Call<ResponseDetailData>
}