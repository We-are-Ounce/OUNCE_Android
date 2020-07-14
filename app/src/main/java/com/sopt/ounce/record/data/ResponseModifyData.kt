package com.sopt.ounce.login.data


data class ResponseModifyData(
    val data: Data?,
    val message: String,
    val status: Int,
    val success: Boolean
){
    data class Data(
        val updateReview : Boolean
    )
}