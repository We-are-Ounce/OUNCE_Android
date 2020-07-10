package com.sopt.ounce.productreview.recyclerview

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sopt.ounce.R

class ProductReviewViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val img_product_review_itemimg =  itemView.findViewById<ImageView>(
    R.id.img_product_review_itemimg)
    val tv_product_review_catname =  itemView.findViewById<TextView>(
        R.id.tv_product_review_catname)
    val tv_product_review_age =  itemView.findViewById<TextView>(
        R.id.tv_product_review_age)
    val tv_product_review_review =  itemView.findViewById<TextView>(
        R.id.tv_product_review_review)
    val tv_product_review_staramount =  itemView.findViewById<TextView>(
        R.id.tv_product_review_staramount)
    val tv_product_review_heartamount =  itemView.findViewById<TextView>(
        R.id.tv_product_review_heartamount)

    fun onBind(productReviewData: ProductReviewData){
        img_product_review_itemimg.setImageResource(productReviewData.img_product_review_itemimg)
        tv_product_review_catname.text = productReviewData.tv_product_review_catname
        tv_product_review_age.text = productReviewData.tv_product_review_age
        tv_product_review_review.text = productReviewData.tv_product_review_review
        tv_product_review_staramount.text = productReviewData.tv_product_review_staramount
        tv_product_review_heartamount.text = productReviewData.tv_product_review_heartamount
    }
}