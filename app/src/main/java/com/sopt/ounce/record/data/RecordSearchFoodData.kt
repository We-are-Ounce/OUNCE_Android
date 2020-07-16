package com.sopt.ounce.record.data

import java.io.Serializable

data class RecordSearchFoodData (
    val foodIdx : Int,
    val foodImg : String,
    val foodManu : String,
    val foodName : String
) : Serializable