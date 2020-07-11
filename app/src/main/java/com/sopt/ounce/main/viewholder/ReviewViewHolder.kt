package com.sopt.ounce.main.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sopt.ounce.R
import com.sopt.ounce.main.data.ReviewData

class ReviewViewHolder (view : View) : RecyclerView.ViewHolder(view){
    private val profile : ImageView = view.findViewById(R.id.img_main_goods_img)
    private val company : TextView = view.findViewById(R.id.tv_main_goods_company)
    private val title : TextView = view.findViewById(R.id.tv_main_goods_name)
    private val review : TextView = view.findViewById(R.id.tv_main_goods_review)
    private val heart : TextView = view.findViewById(R.id.tv_main_goods_heartamount)
    private val star : TextView = view.findViewById(R.id.tv_main_goods_staramount)

    fun onBind(data : ReviewData){
        Glide.with(itemView).load(data.goodsProfile)
            .error(R.drawable.img_food)
            .into(profile)

        company.text = data.company
        title.text = data.title
        review.text = data.goodsIntro
        heart.text = data.heart.toString()
        star.text = data.star.toString()

    }
}
