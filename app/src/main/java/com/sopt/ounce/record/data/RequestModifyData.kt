package com.sopt.ounce.record.data

data class RequestModifyData (
    var reviewRating : Int,
    var reviewPrefer :Int,
    var reviewInfo : String,
    var reviewMemo : String,
    var reviewStatus : Int,
    var reviewSmell : Int,
    var reviewEye: Int,
    var reviewEar :Int,
    var reviewHair : Int,
    var reviewVomit : Int,
    var foodIdx : Int,
    var profileIdx :Int

)