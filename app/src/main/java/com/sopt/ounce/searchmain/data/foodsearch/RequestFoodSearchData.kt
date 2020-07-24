package com.sopt.ounce.searchmain.data.foodsearch

data class RequestFoodSearchData (
    val searchKeyword : String,
    val pageStart : Int,
    val pageEnd : Int
)