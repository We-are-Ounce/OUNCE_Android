package com.sopt.ounce.catregister.data

data class ResponseCatProfileData(
    val status : Int,
    val success : Boolean,
    val data : Data
){
    data class Data(
        val profileIdx : Int
    )
}