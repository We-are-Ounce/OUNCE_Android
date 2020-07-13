package com.sopt.ounce.login.api

import retrofit2.http.POST

interface LoginService {
    @POST("user/signin")
    fun postSignIn()
}