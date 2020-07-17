# MultiPart를 이용한 이미지 업로드 하기

## 구현 방법

### MultPart 어노테이션을 적용한 인터페이스 구성

```
    @Multipart
    @POST("profile/register")
    fun postCatProfile(
        @Header("token") token: String,
        @Part profileImg: MultipartBody.Part,
        @PartMap body: HashMap<String, RequestBody>
    ): Call<ResponseCatProfileData>
```

### 이미지 uri를 MultiPartBody.Part 타입으로 변경

```
    private fun settingImgMultiPart(): MultipartBody.Part {
        val options = BitmapFactory.Options()
        val inputStream = contentResolver.openInputStream(CatInfoData.catProfileUri!!)!!
        val bitmap = BitmapFactory.decodeStream(inputStream, null, options)
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap!!.compress(Bitmap.CompressFormat.JPEG, 20, byteArrayOutputStream)
        val photoBody =
            RequestBody.create(MediaType.parse("image/jpg"), byteArrayOutputStream.toByteArray())

        return MultipartBody.Part.createFormData(
            "profileImg",
            File(CatInfoData.catProfileUri.toString()).name,
            photoBody
        )
    }
```

### 나머지 String 값은 HashMap으로 설정

```
val name = RequestBody.create(MediaType.parse(PARSE_STRING), CatInfoData.profileName)

...

mOunce.SERVICE.postCatProfile(
            token = accessToken,
            profileImg = pictureRb,
            body = hashMapOf<String, RequestBody>(
                "profileName" to name,
                "profileWeight" to weight,
                "profileGender" to gender,
                "profileNeutral" to neutral,
                "profileAge" to age,
                "profileInfo" to info
            )
        ).customEnqueue ....
```
