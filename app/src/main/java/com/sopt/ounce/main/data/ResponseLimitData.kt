package com.sopt.ounce.main.data

data class ResponseLimitData(
    val status : Int,
    val success : Boolean,
    val data : Data
){
    data class Data(
        val possibleAddProfile : Boolean
    )
}