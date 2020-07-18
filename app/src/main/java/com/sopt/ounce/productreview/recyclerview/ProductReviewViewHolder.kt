package com.sopt.ounce.productreview.recyclerview

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.sopt.ounce.R
import com.sopt.ounce.searchmain.data.showreview.ReviewData

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

    fun onBind(reviewData : ReviewData){

        Glide.with(itemView)
            .load(reviewData.profileImg)
            .error(R.drawable.ic_empty)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .into(img_product_review_itemimg)

        img_product_review_itemimg.setImageResource(R.drawable.ic_empty)
        tv_product_review_catname.text = reviewData.profileName
        tv_product_review_age.text = reviewData.profileAge.toString()
        tv_product_review_review.text = reviewData.profileInfo
        tv_product_review_staramount.text = reviewData.reviewRating
        tv_product_review_heartamount.text = reviewData.reviewPrefer
    }
}