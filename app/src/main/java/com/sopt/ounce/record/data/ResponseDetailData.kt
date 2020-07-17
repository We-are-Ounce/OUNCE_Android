package com.sopt.ounce.record.data

import java.io.Serializable

data class ResponseDetailData(
    var data: List<Data>,
    val message: String,
    val status: Int,
    val success: Boolean
){
    data class Data(
        var foodIdx : Int,
        var foodImg : String,
        var foodManu : String,
        var foodName : String,
        var foodDry : String,
        var foodMeat1 : String,
        var foodMeat2 : String?,
        var createdAt : String,
        var reviewRating : String,
        var reviewPrefer : String,
        var reviewInfo : String,
        var reviewStatus : Int,
        var reviewSmell : Int,
        var reviewEye : Int,
        var reviewEar : Int,
        var reviewHair : Int,
        var reviewVomit : Int,
        var reviewMemo : String
    ) : Serializable
}