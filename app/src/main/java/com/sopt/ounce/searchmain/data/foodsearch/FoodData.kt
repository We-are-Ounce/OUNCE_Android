package com.sopt.ounce.searchmain.data.foodsearch

import java.io.Serializable

data class FoodData(
    val avgPrefer: Float,
    val avgRating: Float,
    val foodDry: String,
    val foodIdx: Int,
    val foodImg: String,
    val foodLink: String,
    val foodManu: String,
    val foodMeat: String,
//    val foodMeat1: String,
//    val foodMeat2: String,
    val foodName: String,
    val reviewCount: Int,
    val reviewIdx: Int,
    val reviewInfo: String
) : Serializable