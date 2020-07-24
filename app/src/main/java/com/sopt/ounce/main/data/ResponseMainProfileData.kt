package com.sopt.ounce.main.data


data class ResponseMainProfileData(
    val status : Int,
    val success : Boolean,
    val data : Data
){
    data class Data(
        val reviewCountAll : Int,
        val profileInfoArray : List<Profile>
    ){
        data class Profile(
            val profileImg : String,
            val profileName : String,
            val profileGender : String,
            val profileNeutral : String,
            val profileAge : Int,
            val profileWeight : String,
            val profileInfo : String,
            val follower : Int,
            val following : Int
        )
    }
}