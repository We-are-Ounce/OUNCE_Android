package com.sopt.ounce.productreview

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.amn.easysharedpreferences.EasySharedPreference
import com.bumptech.glide.Glide
import com.google.android.material.appbar.AppBarLayout
import com.sopt.ounce.R
import com.sopt.ounce.productreview.recyclerview.ProductReviewAdapter
import com.sopt.ounce.productreview.recyclerview.ProductReviewData
import com.sopt.ounce.productreview.recyclerview.ProductReviewItemDecoration
import com.sopt.ounce.record.data.RecordSearchFoodData
import com.sopt.ounce.record.ui.RecordActivity
import com.sopt.ounce.searchmain.data.foodsearch.FoodData
import com.sopt.ounce.searchmain.data.showreview.ReviewData
import com.sopt.ounce.util.StatusObject
import kotlinx.android.synthetic.main.activity_product_review.*
import kotlinx.android.synthetic.main.activity_record.*
import kotlin.math.abs

class ProductReviewActivity : AppCompatActivity() {
    lateinit var productReviewAdapter: ProductReviewAdapter
    var mReviewData = mutableListOf<ProductReviewData>()
    //내가 이 제품에 대한 리뷰를 작성했는 지
    var isWritten = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_review)

        StatusObject.setStatusBar(this)

        val intent = intent
        val dataFood : FoodData = intent.getSerializableExtra("foodInfo") as FoodData
        var dataArrayReview : Array<ReviewData> = intent.getSerializableExtra("foodReview") as Array<ReviewData>
        val dataListReview = dataArrayReview.toList()
        val foodLink = dataFood.foodLink

        Glide.with(this).load(dataFood.foodImg).into(img_product_review_goodsimg)
        tv_product_review_company.text = dataFood.foodManu
        tv_product_review_product.text = dataFood.foodName
        tv_product_review_isdry.text = dataFood.foodDry
        if(!dataFood.foodMeat1.isNullOrBlank()){
            tv_product_reivew_meat_1.text = dataFood.foodMeat1
        }else{
            tv_product_reivew_meat_1.visibility = View.GONE
        }

        if(!dataFood.foodMeat2.isNullOrBlank()){
            tv_product_reivew_meat_2.text = dataFood.foodMeat2
        }else{
            tv_product_reivew_meat_2.visibility = View.GONE
        }
        tv_product_review_totalreviewamount.text = dataFood.reviewCount.toString()
        tv_product_review_reviewaverageamount.text = dataFood.avgRating.toString()
        tv_product_review_goodaverageamount.text = dataFood.avgPrefer.toString()

        val myProfileIdx = EasySharedPreference.getInt("profileIdx", 0)
        for(itemReview in dataListReview){
            if(myProfileIdx == itemReview.profileIdx)
                isWritten = true
        }
        //내 리뷰가 저 안에 들어있음
        if(isWritten) {
            tv_product_review_isreview.text = "이미 리뷰를 작성했습니다."
            tv_product_review_isreview.setTextColor(resources.getColor(R.color.black_two))
        }

        //뒤로가기 버튼 누르면 액티비티가 끝나게
        img_product_review_back.setOnClickListener(object : View.OnClickListener{
            override fun onClick(view: View?) {
                this@ProductReviewActivity.finish()
            }
        })

        img_product_review_back_unfocus.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                this@ProductReviewActivity.finish()
            }
        })

        //작성 버튼 클릭 리스너
        img_product_review_write.setOnClickListener(object : View.OnClickListener{
            override fun onClick(view: View?) {
                val myRecordSearchFoodData = RecordSearchFoodData(
                    dataFood.foodIdx,
                    dataFood.foodImg,
                    dataFood.foodManu,
                    dataFood.foodName,
                    dataFood.foodDry,
                    dataFood.foodMeat1,
                    dataFood.foodMeat2
                )
                val intent = Intent(view!!.context, RecordActivity::class.java)
                intent.putExtra("foodItem", myRecordSearchFoodData)
                view!!.context.startActivity(intent)
                //내가 리뷰를 작성 안 했는데 작성버튼을 누르면 리뷰 작성
//                if(!isWritten){
//
//
//                }
//                //내가 리뷰를 작성한 제품의 작성버튼을 누르면 리뷰 수정
//                else{
//
//                }
            }

        })

        productReviewAdapter = ProductReviewAdapter(this)
        rv_product_review_reviews.adapter = productReviewAdapter
        productReviewAdapter.datas = dataListReview
        productReviewAdapter.notifyDataSetChanged()
        rv_product_review_reviews.addItemDecoration(ProductReviewItemDecoration())

        tbar_product_review_productname.text = tv_product_review_product.text
        appbar_product_review.addOnOffsetChangedListener(object: AppBarLayout.OnOffsetChangedListener{
            override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
                if(verticalOffset > -860) {
                    tbar_product_review_top.visibility = View.VISIBLE
                    tbar_product_review_bottom.visibility = View.GONE
                    tbar_product_review_top.alpha = (860 - abs(verticalOffset.toFloat()))/860
                }
                else{
                    tbar_product_review_top.visibility = View.GONE
                    tbar_product_review_bottom.visibility = View.VISIBLE

                }
            }
        })
        imgbtn_product_review_ic_info.setOnClickListener(object : View.OnClickListener{
            override fun onClick(view: View?) {
                val intentWeb = Intent(view!!.context, WebViewActivity::class.java)
                intentWeb.putExtra("webPage", foodLink)
                view!!.context.startActivity(intentWeb)
            }

        })
    }
}