package com.sopt.ounce.login.data

import com.google.gson.annotations.SerializedName

data class ResponseLoginData(
    val data: Data?,
    val message: String,
    val status: Int,
    val success: Boolean
){
    data class Data(
        val accessToken: String,
        val profileCount: Int,
        val profileIdx: Int
    )
}