package com.sopt.ounce.main.data

import java.io.Serializable

data class ReviewData(
    val goodsProfile : String="",
    var company : String="",
    var title : String="",
    var goodsIntro : String="",
    var star : Int=0,
    var heart : Int=0
) : Serializable