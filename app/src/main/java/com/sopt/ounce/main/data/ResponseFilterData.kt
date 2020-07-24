package com.sopt.ounce.main.data

data class ResponseFilterData(
    val message : String,
    val data : List<Data>
){
    data class Data(
        val foodManu : String
    )
}