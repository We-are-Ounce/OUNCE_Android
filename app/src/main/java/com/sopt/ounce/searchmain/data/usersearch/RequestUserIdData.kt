package com.sopt.ounce.searchmain.data.usersearch

data class RequestUserIdData(
    val userId : String,
    val pageStart : Int,
    val pageEnd : Int
)