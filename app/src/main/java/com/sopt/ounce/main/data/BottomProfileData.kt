package com.sopt.ounce.main.data

data class BottomProfileData(
    val status : Int,
    val success : Boolean,
    val message : String,
    val data : List<Data>
){
    data class Data(
        val profileIdx : Int,
        val profileImg : String,
        val profileName : String,
        val profileInfo : String
    )
}