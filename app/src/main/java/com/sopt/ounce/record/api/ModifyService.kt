package com.sopt.ounce.login.api

import com.sopt.ounce.login.data.RequestModifyData
import com.sopt.ounce.login.data.RequestReviewData
import com.sopt.ounce.login.data.ResponseModifyData
import com.sopt.ounce.login.data.ResponseReviewData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.PUT

interface ModifyService {
    @PUT("review/update/:reviewIdx")
    fun putModifyAdd(
        @Body body : RequestModifyData
    ) : Call<ResponseModifyData>
}