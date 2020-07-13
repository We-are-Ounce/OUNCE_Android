package com.sopt.ounce.login.data

data class RequestDetailData (
    var reviewRating : Int,
    var reviewPrefer :Int,
    var reviewInfo : String,
    var reviewMemo : String,
    var reviewStatus : Int,
    var reviewSmell : Int,
    var reviewEye: Boolean,
    var reviewEar :Boolean,
    var reviewHair : Boolean,
    var reviewVomit : Boolean

)