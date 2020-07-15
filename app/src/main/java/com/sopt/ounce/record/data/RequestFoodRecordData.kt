package com.sopt.ounce.record.data

data class RequestFoodRecordData (
    val searchKeyword : String,
    val pageStart : Int,
    val pageEnd : Int
)