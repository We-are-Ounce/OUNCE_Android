package com.sopt.ounce.record.data

import com.sopt.ounce.searchmain.data.foodsearch.FoodData

data class ResponseFoodRecordData (
    val data: List<FoodData>,
    val message: String,
    val status: Int,
    val success: Boolean
)