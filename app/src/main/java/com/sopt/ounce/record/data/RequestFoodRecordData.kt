package com.sopt.ounce.record.data

import java.io.Serializable

data class RequestFoodRecordData (
    val searchKeyword : String,
    val pageStart : String,
    val pageEnd : String
) : Serializable