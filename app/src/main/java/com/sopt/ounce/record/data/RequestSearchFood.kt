package com.sopt.ounce.record.data

data class RequestSearchFood (
    var foodIdx: Int ,
    var foodMeat :String,
    var foodDry : String,
    var foodImg : String,
    var foodManu :String,
    var foodName : String,
    var foodLink: String,
    var reviewCount :Int,
    var reviewIdx : Int,
    var reviewInfo  : String,
    var avgRating :Int,
    var avgPrefer : Int
)