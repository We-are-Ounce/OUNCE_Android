package com.sopt.ounce.login.api

import com.sopt.ounce.login.data.RequestReviewData
import com.sopt.ounce.login.data.ResponseReviewData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RecordService {
    @POST("review/add")
    fun postReviewAdd(
        @Body body : RequestReviewData
    ) : Call<ResponseReviewData>
}