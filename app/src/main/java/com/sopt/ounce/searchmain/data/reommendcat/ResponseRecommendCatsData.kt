package com.sopt.ounce.searchmain.data.reommendcat

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ResponseRecommendCatsData(
    val data: Data,
    val message: String,
    val status: Int,
    val success: Boolean
){
    data class Data(
        val recommendFoodList: List<RecommendFood>,
        val resultProfile: List<ResultProfile>,
        val similarity: List<Int>
    ){
        data class RecommendFood(
            val foodImg: String,
            val profileIdx: Int
        )

        data class ResultProfile(
            val profileIdx: Int,
            val profileImg: String,
            val profileName: String
        )


    }
}





