package com.sopt.ounce.server

import com.sopt.ounce.login.data.RequestLoginData
import com.sopt.ounce.login.data.ResponseLoginData
import com.sopt.ounce.searchmain.data.reommendcat.RequestRecommendCatsData
import com.sopt.ounce.searchmain.data.reommendcat.ResponseRecommendCatsData
import com.sopt.ounce.searchmain.data.usersearch.RequestUserIdData
import com.sopt.ounce.searchmain.data.usersearch.ResponseUserSearchData
import com.sopt.ounce.signup.data.RequestSignUpdata
import com.sopt.ounce.signup.data.ResponseSignUpData
import com.sopt.ounce.signup.data.UserInfoObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface OunceService {
    @POST("user/signin")
    fun postSignIn(
        @Body body : RequestLoginData
    ) : Call<ResponseLoginData>

    @POST("user/signup")
    fun postSignUp(
        @Body body : RequestSignUpdata
    ) : Call<ResponseSignUpData>

    @POST("search/recommend")
    fun requestRecommendCat(
        @Body body : RequestRecommendCatsData
    ) : Call<ResponseRecommendCatsData>

    @POST("search/user")
    fun postUserSearch(
        @Body body : RequestUserIdData
    ) : Call<ResponseUserSearchData>
}