package com.sopt.ounce.productreview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sopt.ounce.R
import com.sopt.ounce.productreview.recyclerview.ProductReviewAdapter
import com.sopt.ounce.productreview.recyclerview.ProductReviewData
import com.sopt.ounce.util.StatusObject
import kotlinx.android.synthetic.main.activity_product_review.*

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
        }
        productReviewAdapter.datas = mReviewData
        productReviewAdapter.notifyDataSetChanged()
    }
}