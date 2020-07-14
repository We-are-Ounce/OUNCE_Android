package com.sopt.ounce.server

import com.sopt.ounce.catregister.data.ResponseCatProfileData
import com.sopt.ounce.login.data.RequestLoginData
import com.sopt.ounce.login.data.ResponseLoginData
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
        // profileImg = pictureRb
        @Part profileImg: MultipartBody.Part,
        //실제 사용 시
        //val name = RequestBody.create(MediaType.parse("text/plain"), 값)
        @PartMap body: HashMap<String, RequestBody>
    ): Call<ResponseCatProfileData>
}