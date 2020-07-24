# 캣푸드 기록하기

## 핵심 기능

:point_right: 최근 검색 기록 확인, 삭제 기능
:point_right: 제품 이름 검색시, RecyclerView에 제품 보여주는 기능  
 :point_right: 해당 제품 클릭시, 제품에 대한 리뷰를 남기거나, 다른 사람이 남긴 리뷰를 볼 수 있는 기능
:point_right: 반려묘와 유사한 입맛의 다른 캣푸드 확인 기능
:point_right: 선택한 캣푸드의 제조 회사, 형태, 이미지, 총점, 기호도, 부작용 등을 보거나 기록할 수 있는 기능
:point_right: 자신이 기록한 캣푸드 리뷰 삭제, 수정 기능

## RecordActivity

- XML : ScrollView 안에 ConstraintLayout 을 사용하여 설계

- 총점과 기호도는 RatingBar을 이용해서 평점을 주고, RatingBar Custom은 다음과 같이 작성해주었다.

```
<com.hedgehog.ratingbar.RatingBar
android:id="@+id/ratingBar"
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:layout_gravity="center"
android:layout_marginStart="28dp"
android:paddingBottom="8dp"
app:clickable="true"
app:layout_constraintBottom_toBottomOf="@+id/textView4"
app:layout_constraintStart_toEndOf="@+id/textView4"
app:starCount="5"
app:starEmpty="@drawable/ic_total_unselected"
app:starFill="@drawable/ic_evaluate_full"
app:starImageHeight="32dp"
app:starImageWidth="32dp" />
```

- 변상태와 변냄새를 나타내는 부분은 chip을 사용해서 구현

## RecordModifyActivity

- 캣푸드 기록을 직접 수정하거나 삭제할 수 있고, popupmenu 클릭시, 수정, 삭제 버튼이 뜨고 내용 수정이 가능하며 수정된 내용이 저장된다.

```
        val popup = PopupMenu(this, btn_record_popup)
        popup.inflate(R.menu.record_popup)
        popup.setOnMenuItemClickListener {
            when (it.title) {
                "수정" -> {
                    record_update_button.visibility = View.VISIBLE
                }
                "삭제" -> {

                    mModifyRequest.SERVICE.deleteDataReview(
                        mAccessToken,
                        mProfileIdx,
                        mReviewIdx
                    ).customEnqueue(
                        onSuccess = {
                            Toast.makeText(
                                this@RecordModifyActivity,
                                "내가 쓴 리뷰 삭제 성공",
                                Toast.LENGTH_SHORT
                            ).show()
                            finish()
                        },
                        onError = {
                            Toast.makeText(
                                this@RecordModifyActivity,
                                "리뷰 삭제 권한이 없습니다.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                    )
                }

            }
            true
        }
```

## ImageSearchActivity

- SearchView 사용해서 캣푸드 검색, RecyclerView에 뿌려주기
- Adapter와 Viewholder 사용
- ItemDecoration을 통해 Recyclerview 간격 다듬어주기

## Retrofit - OunceService

- 서버 통신

```
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
      @Path("profileIdx") profileIdx : Int,
      @Path("reviewIdx") reviewIdx: Int
    ) : Call<ResponseDeleteData>
```
