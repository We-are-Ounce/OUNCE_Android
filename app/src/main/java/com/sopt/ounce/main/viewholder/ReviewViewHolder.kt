package com.sopt.ounce.main.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sopt.ounce.R
import com.sopt.ounce.main.data.ResponseMainReviewData

class ReviewViewHolder (view : View) : RecyclerView.ViewHolder(view){
    private val profile : ImageView = view.findViewById(R.id.img_main_goods_img)
    private val company : TextView = view.findViewById(R.id.tv_main_goods_company)
    private val title : TextView = view.findViewById(R.id.tv_main_goods_name)
    private val review : TextView = view.findViewById(R.id.tv_main_goods_review)
    private val heart : TextView = view.findViewById(R.id.tv_main_goods_heartamount)
    private val star : TextView = view.findViewById(R.id.tv_main_goods_staramount)

    fun onBind(data : ResponseMainReviewData.Data){
        Glide.with(itemView).load(data.foodImg)
            .error(R.drawable.img_food)
            .into(profile)

        company.text = data.foodManu
        title.text = data.foodName
        review.text = data.reviewInfo
        heart.text = data.reviewPrefer.toString()
        star.text = data.reviewRating.toString()

    }
}
