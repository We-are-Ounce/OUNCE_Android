package com.sopt.ounce.record.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sopt.ounce.R
import com.sopt.ounce.record.data.ItemData
import com.sopt.ounce.searchmain.data.foodsearch.FoodData

class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val cardview_image = itemView.findViewById<ImageView>(R.id.cardview_image)
    val cardview_item = itemView.findViewById<TextView>(R.id.cardview_item)
    val cardview_itemname = itemView.findViewById<TextView>(R.id.cardview_itemname)
    var avgPrefer = 0F
    var avgRating = 0F
    var foodDry = ""
    var foodIdx = 0
    var foodLink = ""
    var foodMeat = ""
    var reviewCount = 0
    var reviewIdx = 0
    var reviewInfo = ""


    fun onBind(foodData: FoodData){
        Glide.with(itemView).load(foodData.foodImg).into(cardview_image)
        cardview_itemname.text = foodData.foodManu
        cardview_item.text = foodData.foodName
        avgPrefer = foodData.avgPrefer
        avgRating = foodData.avgRating
        foodDry = foodData.foodDry
        foodIdx = foodData.foodIdx
        foodLink = foodData.foodLink
        foodMeat = foodData.foodMeat
        reviewCount = foodData.reviewCount
        reviewIdx = foodData.reviewIdx
    }
}