package com.sopt.ounce.server

import com.sopt.ounce.login.data.*
import com.sopt.ounce.signup.data.RequestSignUpdata
import com.sopt.ounce.signup.data.ResponseSignUpData
import com.sopt.ounce.signup.data.UserInfoObject
import retrofit2.Call
import retrofit2.http.*

interface OunceService {
    @POST("user/signin")
    fun postSignIn(
        @Body body : RequestLoginData
    ) : Call<ResponseLoginData>

    @POST("user/signup")
    fun postSignUp(
        @Body body : RequestSignUpdata
    ) : Call<ResponseSignUpData>

    //review
    @Headers("Content-Type:application/json")
    @POST("review/add")
    fun postAddReview(
        @Header("Token") token : String,
        @Body body : RequestReviewData
    ) : Call<ResponseReviewData>

    @Headers("Content-Type:application/json")
    @PUT("review/update/:reviewIdx")
    fun putUpdateReview(
        @Header("Token") accessToken : String,
        @Path ("reviewIdx") reviewIdx: Int,
        @Body body : RequestModifyData
    ) : Call<ResponseModifyData>


    @Headers("Content-Type:application/json")
    @GET("review/detail/:reviewIdx")
    fun getDetailReview(
        @Path("reviewIdx") reviewIdx: Int
    ) : Call<ResponseDetailData>


    @Headers("Content-Type:application/json")
    @DELETE("review/delete/:profileIdx/:reviewIdx")
    fun deleteDataReview(
        @Header("token") accessToken: String,
        @Path("reviewIdx") reviewIdx: Int
    ) : Call<ResponseDeleteData>
    //
}