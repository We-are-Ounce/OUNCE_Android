package com.sopt.ounce.record.data

data class ResponseFoodRecordData (
    val data: List<RecordSearchFoodData>,
    val message: String,
    val status: Int,
    val success: Boolean
)