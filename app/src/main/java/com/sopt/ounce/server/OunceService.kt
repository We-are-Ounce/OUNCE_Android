package com.sopt.ounce.server


import com.sopt.ounce.catregister.data.ResponseCatProfileData
import com.sopt.ounce.login.data.RequestLoginData
import com.sopt.ounce.login.data.ResponseLoginData
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


    @Multipart
    @POST("profile/register")
    fun postCatProfile(
        @Header("Token") token: String,
        // profileImg = pictureRb
        @Part profileImg: MultipartBody.Part,
        //실제 사용 시
        //val name = RequestBody.create(MediaType.parse("text/plain"), 값)
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
  
}