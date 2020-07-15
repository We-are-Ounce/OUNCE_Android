package com.sopt.ounce.record.data

data class ResponseSearchFood(
    val data: Data?,
    val message: String,
    val status: Int,
    val success: Boolean
){
    data class Data(
        val foodIdx : Int,
        val foodMeat : String,
        val foodDry : String,
        val foodImg : String,
        val foodManu : String,
        val foodName : String,
        val foodLink : String,
        val reviewCount : Int,
        val reviewIdx : Int,
        val reviewInfo : String,
        val avgRating  : Int,
        val avgPrefer : Int
    )
}