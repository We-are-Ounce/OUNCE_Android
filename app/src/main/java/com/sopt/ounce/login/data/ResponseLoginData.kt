package com.sopt.ounce.login.data

data class ResponseLoginData(
    val data: Data,
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