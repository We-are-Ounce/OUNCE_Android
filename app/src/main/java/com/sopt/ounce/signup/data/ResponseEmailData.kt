package com.sopt.ounce.signup.data

data class ResponseEmailData(
    val message : String,
    val data : Data,
    val status : Int
){
    data class Data(
        val state : Boolean,
        val sendTime : String,
        val email : String
    )
}