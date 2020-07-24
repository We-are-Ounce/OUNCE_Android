package com.sopt.ounce.main.data


import java.io.Serializable

data class ResponseReviewData(
    val status : Int,
    val success : Boolean,
    val message : String,
    val data : List<Data>
){
    data class Data(
        val profileIdx : Int,
        val reviewIdx : Int,
        val foodIdx : Int,
        val foodImg : String,
        val foodManu : String,
        val foodName : String,
        val reviewInfo : String,
        val reviewRating : String,
        val reviewPrefer : String,
        val createdAt : String
    ) : Serializable
}