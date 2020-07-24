package com.sopt.ounce.searchmain.data.showreview

import java.io.Serializable

data class ReviewData(
    val foodIdx: Int,
    val profileImg : String,
    val profileAge: Int,
    val profileIdx: Int,
    val profileInfo: String,
    val profileName: String,
    val reviewIdx: Int,
    val reviewPrefer: String,
    val reviewRating: String
) : Serializable