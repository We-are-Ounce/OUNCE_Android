package com.sopt.ounce.searchmain.network.recommendcat

import com.sopt.ounce.searchmain.data.reommendcat.RequestRecommendCatsData
import com.sopt.ounce.searchmain.data.reommendcat.ResponseRecommendCatsData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RequestRecoomendCatInterface {
    @POST("search/user")
    fun requestRecommedCat(@Body body : RequestRecommendCatsData) : Call<ResponseRecommendCatsData>
}