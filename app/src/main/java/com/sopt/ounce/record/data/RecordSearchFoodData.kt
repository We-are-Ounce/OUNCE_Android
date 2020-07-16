package com.sopt.ounce.record.data

import java.io.Serializable

data class RecordSearchFoodData (
    val foodIdx : Int,
    val foodImg : String,
    val foodManu : String,
    val foodName : String,
    val foodDry : String,
    val foodMeat1 : String,
    val foodMeat2 : String?
) : Serializable