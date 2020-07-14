package com.sopt.ounce.catregister.data

import okhttp3.MultipartBody

data class RequestCatProfileData(
    var profileImg : MultipartBody.Part,
    var profileName : String,
    var profileWeight : String,
    var profileGender : String,
    var profileNeutral : Boolean,
    var profileAge : Int,
    var profileInfo : String,
    var userIdx : Int
)