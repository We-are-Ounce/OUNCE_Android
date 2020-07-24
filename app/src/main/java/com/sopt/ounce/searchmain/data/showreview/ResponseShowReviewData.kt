package com.sopt.ounce.searchmain.data.showreview

import java.io.Serializable

data class ResponseShowReviewData(
    val data: List<ReviewData>,
    val message: String,
    val status: Int,
    val success: Boolean
) : Serializable