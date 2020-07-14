package com.sopt.ounce.searchmain.data.reommendcat

import java.io.Serializable

data class ResponseRecommendCatsData(
    val `data`: Data,
    val message: String,
    val status: Int,
    val success: Boolean
):Serializable{
    data class Data(
        val recommendFoodList: List<RecommendFood>,
        val resultProfile: List<ResultProfile>,
        val similarity: List<Int>
    ):Serializable{
        data class RecommendFood(
            val foodImg: String,
            val profileIdx: Int
        ):Serializable

        data class ResultProfile(
            val profileIdx: Int,
            val profileImg: String,
            val profileName: String
        ):Serializable
    }
}