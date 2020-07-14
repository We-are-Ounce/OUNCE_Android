package com.sopt.ounce.login.api

import com.sopt.ounce.login.data.*
import retrofit2.Call
import retrofit2.http.*

interface RecordService {
    @POST("review/add")
    fun postReviewAdd(
        @Body body : RequestReviewData
    ) : Call<ResponseReviewData>

    @PUT("review/update/:" +
            "reviewIdx")
    fun putModifyAdd(
        @Body body : RequestModifyData
    ) : Call<ResponseModifyData>

    @GET("review/detail/:reviewIdx")
    fun getModifyAdd(
        @Body body : RequestDetailData
    ) : Call<ResponseDetailData>

    @DELETE("review/delete/:profileIdx/:reviewIdx")
    fun deleteModifyAdd(
        @Body body : RequestDeleteData
    ) : Call<ResponseDeleteData>
}