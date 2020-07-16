package com.sopt.ounce.record.data

import com.google.gson.annotations.SerializedName

data class ResponseDeleteData(
    val message: String,
    val status: Int,
    val success: Boolean
)