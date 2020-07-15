package com.sopt.ounce.server

import com.sopt.ounce.catregister.data.ResponseCatProfileData
import com.sopt.ounce.login.data.RequestLoginData
import com.sopt.ounce.login.data.ResponseLoginData
import com.sopt.ounce.main.data.ResponseMainProfileData
import com.sopt.ounce.main.data.ResponseMainReviewData
import com.sopt.ounce.searchmain.data.reommendcat.RequestRecommendCatsData
import com.sopt.ounce.searchmain.data.reommendcat.ResponseRecommendCatsData
import com.sopt.ounce.searchmain.data.usersearch.RequestUserIdData
import com.sopt.ounce.searchmain.data.usersearch.ResponseUserSearchData
import com.sopt.ounce.signup.data.RequestSignUpdata
import com.sopt.ounce.signup.data.ResponseSignUpData
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface OunceService {

    @Headers("Content-Type:application/json")
    @POST("user/signin")
    fun postSignIn(
        @Body body: RequestLoginData
    ): Call<ResponseLoginData>

    @Headers("Content-Type:application/json")
    @POST("user/signup")
    fun postSignUp(
        @Body body: RequestSignUpdata
    ): Call<ResponseSignUpData>

    @Multipart
    @POST("profile/register")
    fun postCatProfile(
        @Header("Token") token: String,
        @Part profileImg: MultipartBody.Part,
        @PartMap body: HashMap<String, RequestBody>
    ): Call<ResponseCatProfileData>
  
    @POST("search/recommend")
    fun requestRecommendCat(
        @Body body : RequestRecommendCatsData
    ) : Call<ResponseRecommendCatsData>

    @POST("search/user")
    fun postUserSearch(
        @Body body : RequestUserIdData
    ) : Call<ResponseUserSearchData>


    //메인 화면 뷰 통신 인터페이스
    @GET("profile/mainProfile/{profileIdx}")
    fun getMainProfile(
        @Header("token") token : String,
        @Path("profileIdx") profileIdx : Int
    ) : Call<ResponseMainProfileData>


    @GET("review/{profileIdx}/prefer")
    fun getMainReview(
        @Path("profileIdx")profileIdx: Int,
        @Query("pageStart") start : Int,
        @Query("pageEnd") end : Int
    ) : Call<ResponseMainReviewData>


}