package com.sopt.ounce.searchmain.data.usersearch

data class ResponseUserSearchData(
    val data: List<UserData>,
    val message: String,
    val status: Int,
    val success: Boolean
)