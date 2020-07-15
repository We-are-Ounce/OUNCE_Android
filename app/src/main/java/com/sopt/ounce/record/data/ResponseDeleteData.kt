package com.sopt.ounce.login.data

import com.google.gson.annotations.SerializedName

data class ResponseDeleteData(
    val message: String,
    val status: Int,
    val success: Boolean
)