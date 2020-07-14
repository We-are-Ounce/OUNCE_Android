package com.sopt.ounce.signup.data

data class ResponseSignUpData(
    val statue : Int,
    val success : Boolean,
    val message : String,
    val data : Data?
){
    data class Data(
        val accessToken : String,
        val userIdx : String
    )
}