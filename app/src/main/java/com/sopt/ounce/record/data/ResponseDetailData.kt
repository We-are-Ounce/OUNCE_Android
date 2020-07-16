package com.sopt.ounce.record.data

import java.io.Serializable

data class ResponseDetailData(
    val data: Data?,
    val message: String,
    val status: Int,
    val success: Boolean
){
    data class Data(
        val reviewRating : Int,
        val reviewPrefer :Int,
        val reviewInfo : String,
        val reviewMemo : String,
        val reviewStatus : Int,
        val reviewSmell : Int,
        val reviewEye: Boolean,
        val reviewEar :Boolean,
        val reviewHair : Boolean,
        val reviewVomit : Boolean,
        val createdAt : String,
        val foodIdx : Int,
        val profileIdx :Int
    ) : Serializable
}