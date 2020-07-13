package com.sopt.ounce.login.api

import com.sopt.ounce.login.data.RequestLoginData
import com.sopt.ounce.login.data.ResponseLoginData
import com.sopt.ounce.signup.data.RequestSignUpdata
import com.sopt.ounce.signup.data.ResponseSignUpData
import com.sopt.ounce.signup.data.UserInfoObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("user/signin")
    fun postSignIn(
        @Body body : RequestLoginData
    ) : Call<ResponseLoginData>

    @POST("user/signup")
    fun postSignUp(
        @Body body : RequestSignUpdata
    ) : Call<ResponseSignUpData>
}