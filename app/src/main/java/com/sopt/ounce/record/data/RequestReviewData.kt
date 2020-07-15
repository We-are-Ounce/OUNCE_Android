package com.sopt.ounce.login.data

data class RequestReviewData (
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