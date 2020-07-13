package com.sopt.ounce.login.api

import com.sopt.ounce.login.data.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.PUT

interface DeleteService {
    @DELETE("review/delete/:profileIdx/:reviewIdx")
    fun putModifyAdd(
        @Body body : RequestDeleteData
    ) : Call<ResponseDeleteData>
}