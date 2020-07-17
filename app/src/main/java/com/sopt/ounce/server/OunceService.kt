package com.sopt.ounce.server


import com.sopt.ounce.catregister.data.ResponseCatProfileData
import com.sopt.ounce.login.data.*
import com.sopt.ounce.main.data.BottomProfileData
import com.sopt.ounce.main.data.ResponseMainProfileData
import com.sopt.ounce.record.data.*
import com.sopt.ounce.main.data.*
import com.sopt.ounce.main.data.ResponseReviewData
import com.sopt.ounce.record.data.RequestFoodRecordData
import com.sopt.ounce.record.data.ResponseFoodRecordData
import com.sopt.ounce.searchmain.data.foodsearch.RequestFoodSearchData
import com.sopt.ounce.searchmain.data.foodsearch.ResponseFoodSearchData
import com.sopt.ounce.searchmain.data.reommendcat.RequestRecommendCatsData
import com.sopt.ounce.searchmain.data.reommendcat.ResponseRecommendCatsData
import com.sopt.ounce.searchmain.data.showreview.RequestShowReviewData
import com.sopt.ounce.searchmain.data.showreview.ResponseShowReviewData
import com.sopt.ounce.searchmain.data.usersearch.RequestUserIdData
import com.sopt.ounce.searchmain.data.usersearch.ResponseUserSearchData
import com.sopt.ounce.signup.data.RequestSignUpdata
import com.sopt.ounce.signup.data.ResponseSignUpData
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface OunceService {
    //로그인 회원가입 인터페이스 //////////
    @Headers("Content-Type:application/json")
    @POST("user/signin")
    fun postSignIn(
        @Body body: RequestLoginData
    ): Call<ResponseLoginData>

    @Headers("Content-Type:application/json")
    @POST("user/signup")
    fun postSignUp(
        @Body body : RequestSignUpdata
    ) : Call<ResponseSignUpData>

    @Multipart
    @POST("profile/register")
    fun postCatProfile(
        @Header("token") token: String,
        @Part profileImg: MultipartBody.Part,
        @PartMap body: HashMap<String, RequestBody>
    ): Call<ResponseCatProfileData>

    @Headers("Content-Type:application/json")
    @POST("profile/limitProfile")
    fun postIsLimit(
        @Header("token") token : String
    ) : Call<ResponseLimitData>

    //////////////////////////////////////

    //review
    @Headers("Content-Type:application/json")
    @POST("review/add")
    fun postAddReview(
        @Header("Token") token : String,
        @Body body : RequestRecordReviewData
    ) : Call<ResponseRecordReviewData>

    @Headers("Content-Type:application/json")
    @PUT("review/update/{reviewIdx}")
    fun putUpdateReview(
        @Header("Token") accessToken : String,
        @Path ("reviewIdx") reviewIdx: Int,
        @Body body : RequestModifyData
    ) : Call<ResponseModifyData>


    @Headers("Content-Type:application/json")
    @GET("review/detail/{reviewIdx}")
    fun getDetailReview(
        @Path("reviewIdx") reviewIdx: Int
    ) : Call<ResponseDetailData>


    @Headers("Content-Type:application/json")
    @DELETE("review/delete/{profileIdx}/{reviewIdx}")
    fun deleteDataReview(
        @Header("token") accessToken: String,
        @Path("reviewIdx") reviewIdx: Int
    ) : Call<ResponseDeleteData>


    @Headers("Content-Type:application/json")
    @POST("search/recommend")
    fun requestRecommendCat(
        @Body body : RequestRecommendCatsData
    ) : Call<ResponseRecommendCatsData>

    @Headers("Content-Type:application/json")
    @POST("search/user")
    fun postUserSearch(
        @Body body : RequestUserIdData
    ) : Call<ResponseUserSearchData>


    //메인 화면 뷰 통신 인터페이스//
    @GET("profile/mainProfile/{profileIdx}")
    fun getMainProfile(
        @Header("token") token : String,
        @Path("profileIdx") profileIdx : Int
    ) : Call<ResponseMainProfileData>


    @GET("review/{profileIdx}/date")
    fun getMainReview(
        @Path("profileIdx")profileIdx: Int,
        @Query("pageStart") start : Int,
        @Query("pageEnd") end : Int
    ) : Call<ResponseReviewData>

    @GET("review/{profileIdx}/rating")
    fun getRatingReview(
        @Path("profileIdx") profileIdx: Int,
        @Query("pageStart") start : Int,
        @Query("pageEnd") end : Int
    ) : Call<ResponseReviewData>

    @GET("review/{profileIdx}/prefer")
    fun getPreferReview(
        @Path("profileIdx") profileIdx: Int,
        @Query("pageStart") start : Int,
        @Query("pageEnd") end : Int
    ) : Call<ResponseReviewData>

    @Headers("Content-Type:application/json")
    @GET("profile/conversion/{profileIdx}")
    fun getConversionProfile(
        @Header("token") token: String,
        @Path("profileIdx") profileIdx : Int
    ) : Call<BottomProfileData>

    @GET("review/{profileIdx}/category")
    fun getFilterManu(
        @Path("profileIdx") profileIdx: Int
    ) : Call<ResponseFilterData>

    @Headers("Content-Type:application/json")
    @POST("review/{profileIdx}/filter")
    fun postSelectFiltering(
        @Path("profileIdx") profileIdx: Int,
        @Body body : RequestSelectedFilter
    ) : Call<ResponseReviewData>
    /////////////////////////////////////////////

    ///다른 프로필 조회 인터페이스 ////////////////
    @Headers("Content-Type:application/json")
    @GET("profile")
    fun getOtherProfile(
        @Query("myprofileIdx") myprofileIdx : Int,
        @Query("profileIdx") otherIdx : Int
    ) : Call<ResponseOtherProfileData>

    @GET("review/{profileIdx}/date")
    fun getOtherProfileReview(
        @Path("profileIdx") profileIdx : Int,
        @Query("pageStart") pageStart : Int,
        @Query("pageEnd") pageEnd : Int
    ) : Call<ResponseReviewData>

    @Headers("Content-Type:application/json")
    @POST("profile/requestFollow")
    fun postFollow(
        @Body body : RequestFollowData
    ) : Call<ResponseFollowData>

    @Headers("Content-Type:applicaation/json")
    @HTTP(method = "DELETE", path = "profile/deleteFollow", hasBody = true)
    fun deleteFollow(
        @Body body :RequestFollowData
    ) : Call<ResponseFollowData>

    /////////////////////////////////////////////

    @Headers("Content-Type:application/json")
    @POST("search/toWrite/{profileIdx}")
    fun postRecordFoodSearch(
        @Path("profileIdx") profileIdx: Int,
        @Body body : RequestFoodRecordData
    ) : Call<ResponseFoodRecordData>


    @Headers("Content-Type:application/json")
    @POST("search/food")
    fun postFoodSearch(
        @Body body : RequestFoodSearchData
    ) : Call<ResponseFoodSearchData>

    @Headers("Content-Type:application/json")
    @POST("search/reviewAll/avgRating")
    fun postReviewSortTotalScore(
        @Body body : RequestFoodSearchData
    ) : Call<ResponseFoodSearchData>

    @Headers("Content-Type:application/json")
    @POST("search/reviewAll/avgPrefer")
    fun postReviewSortFavorite(
        @Body body : RequestFoodSearchData
    ) : Call<ResponseFoodSearchData>

    @Headers("Content-Type:application/json")
    @POST("search/reviewAll")
    fun postShowReviewAll(
        @Body body : RequestShowReviewData
    ) : Call<ResponseShowReviewData>
  
}