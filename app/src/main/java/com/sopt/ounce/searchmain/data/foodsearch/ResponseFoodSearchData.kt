package com.sopt.ounce.searchmain.data.foodsearch

data class ResponseFoodSearchData(
    val data: List<FoodData>,
    val message: String,
    val status: Int,
    val success: Boolean
)