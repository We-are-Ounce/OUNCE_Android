package com.sopt.ounce.signup.api


import com.sopt.ounce.signup.data.RequestEmailData
import com.sopt.ounce.signup.data.ResponseEmailData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface EmailCheckService {
    @POST("emailto")
    fun postEmail(
        @Body body : RequestEmailData
    ) : Call<ResponseEmailData>
}