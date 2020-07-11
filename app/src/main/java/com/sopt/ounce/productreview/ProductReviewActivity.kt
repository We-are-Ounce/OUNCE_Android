package com.sopt.ounce.productreview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.android.material.appbar.AppBarLayout
import com.sopt.ounce.R
import com.sopt.ounce.productreview.recyclerview.ProductReviewAdapter
import com.sopt.ounce.productreview.recyclerview.ProductReviewData
import com.sopt.ounce.productreview.recyclerview.ProductReviewItemDecoration
import com.sopt.ounce.util.StatusObject
import kotlinx.android.synthetic.main.activity_product_review.*
import kotlinx.android.synthetic.main.activity_record.*
import kotlin.math.abs

class ProductReviewActivity : AppCompatActivity() {
    lateinit var productReviewAdapter: ProductReviewAdapter
    var mReviewData = mutableListOf<ProductReviewData>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_review)

        StatusObject.setStatusBar(this)
        productReviewAdapter = ProductReviewAdapter(this)
        rv_product_review_reviews.adapter = productReviewAdapter
        loadReviewDatas()
        rv_product_review_reviews.addItemDecoration(ProductReviewItemDecoration())

        tbar_product_review_productname.text = tv_product_review_product.text
        appbar_product_review.addOnOffsetChangedListener(object: AppBarLayout.OnOffsetChangedListener{
            override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
                if(verticalOffset > -860) {
                    tbar_product_review_top.visibility = View.VISIBLE
                    tbar_product_review_bottom.visibility = View.GONE
                    tbar_product_review_top.alpha = (860 - abs(verticalOffset.toFloat()))/860
                    //tbar_product_review_bottom.alpha = (abs(verticalOffset.toFloat() - 860)/860)
                }
                else{
                    tbar_product_review_top.visibility = View.GONE
                    tbar_product_review_bottom.visibility = View.VISIBLE

                }
            }
        })
    }


    private fun loadReviewDatas(){
        mReviewData.apply {
            add(
                ProductReviewData(
                    img_product_review_itemimg = R.drawable.img_card_cat,
                    tv_product_review_catname = "Spring",
                    tv_product_review_age = "5살",
                    tv_product_review_review = "우리 고양이가 넘모 잘목네용",
                    tv_product_review_heartamount = "5",
                    tv_product_review_staramount = "3"
                )
            )
            add(
                ProductReviewData(
                    img_product_review_itemimg = R.drawable.img_card_cat,
                    tv_product_review_catname = "Summer",
                    tv_product_review_age = "6살",
                    tv_product_review_review = "우리 고양이가 넘모 잘목네용",
                    tv_product_review_heartamount = "4",
                    tv_product_review_staramount = "3"
                )
            )
            add(
                ProductReviewData(
                    img_product_review_itemimg = R.drawable.img_card_cat,
                    tv_product_review_catname = "Autumn",
                    tv_product_review_age = "5살",
                    tv_product_review_review = "우리 고양이가 넘모 잘목네용",
                    tv_product_review_heartamount = "2",
                    tv_product_review_staramount = "3"
                )
            )
            add(
                ProductReviewData(
                    img_product_review_itemimg = R.drawable.img_card_cat,
                    tv_product_review_catname = "Winter",
                    tv_product_review_age = "5살",
                    tv_product_review_review = "우리 고양이가 넘모 잘목네용",
                    tv_product_review_heartamount = "1",
                    tv_product_review_staramount = "3"
                )
            )
            add(
                ProductReviewData(
                    img_product_review_itemimg = R.drawable.img_card_cat,
                    tv_product_review_catname = "Spring",
                    tv_product_review_age = "5살",
                    tv_product_review_review = "우리 고양이가 쥰 잘목네용",
                    tv_product_review_heartamount = "5",
                    tv_product_review_staramount = "3"
                )
            )
            add(
                ProductReviewData(
                    img_product_review_itemimg = R.drawable.img_card_cat,
                    tv_product_review_catname = "Spring",
                    tv_product_review_age = "5살",
                    tv_product_review_review = "우리 고양이가 넘모 잘목네용",
                    tv_product_review_heartamount = "5",
                    tv_product_review_staramount = "3"
                )
            )
            add(
                ProductReviewData(
                    img_product_review_itemimg = R.drawable.img_card_cat,
                    tv_product_review_catname = "Spring",
                    tv_product_review_age = "5살",
                    tv_product_review_review = "우리 고양이가 넘모 잘목네용",
                    tv_product_review_heartamount = "5",
                    tv_product_review_staramount = "3"
                )
            )
            add(
                ProductReviewData(
                    img_product_review_itemimg = R.drawable.img_card_cat,
                    tv_product_review_catname = "Spring",
                    tv_product_review_age = "5살",
                    tv_product_review_review = "우리 고양이가 넘모 잘목네용",
                    tv_product_review_heartamount = "5",
                    tv_product_review_staramount = "3"
                )
            )
            add(
                ProductReviewData(
                    img_product_review_itemimg = R.drawable.img_card_cat,
                    tv_product_review_catname = "Spring",
                    tv_product_review_age = "5살",
                    tv_product_review_review = "우리 고양이가 넘모 잘목네용",
                    tv_product_review_heartamount = "5",
                    tv_product_review_staramount = "3"
                )
            )
            add(
                ProductReviewData(
                    img_product_review_itemimg = R.drawable.img_card_cat,
                    tv_product_review_catname = "Spring",
                    tv_product_review_age = "5살",
                    tv_product_review_review = "우리 고양이가 넘모 잘목네용",
                    tv_product_review_heartamount = "5",
                    tv_product_review_staramount = "3"
                )
            )
        }
        productReviewAdapter.datas = mReviewData
        productReviewAdapter.notifyDataSetChanged()
    }
}