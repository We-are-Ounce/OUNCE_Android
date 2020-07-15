package com.sopt.ounce.searchmain.recyclerview

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sopt.ounce.R
import com.sopt.ounce.searchmain.data.foodsearch.FoodData

class SearchGoodsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val img_search_goods_goodsimage = itemView.findViewById<ImageView>(
        R.id.img_search_goods_goodsimage)
    val tv_search_goods_company = itemView.findViewById<TextView>(R.id.tv_search_goods_company)
    val tv_search_goods_name = itemView.findViewById<TextView>(R.id.tv_search_goods_name)
    val tv_search_goods_review = itemView.findViewById<TextView>(R.id.tv_search_goods_review)
    val tv_search_goods_staramount = itemView.findViewById<TextView>(R.id.tv_search_goods_staramount)
    val tv_search_goods_heartamount = itemView.findViewById<TextView>(R.id.tv_search_goods_heartamount)
    var foodIdx = 0
    var foodMeat = ""
    var foodDry = ""
    var foodLink = ""
    var reviewCount = 0
    var reviewIdx = 0


    fun onBind(foodData: FoodData){
        Glide.with(itemView).load(foodData.foodImg).into(img_search_goods_goodsimage)
        tv_search_goods_company.text = foodData.foodManu
        tv_search_goods_name.text = foodData.foodName
        tv_search_goods_review.text = foodData.reviewInfo
        when(foodData.avgRating.toInt()){
            1 -> tv_search_goods_staramount.text = "1"
            2 -> tv_search_goods_staramount.text = "2"
            3 -> tv_search_goods_staramount.text = "3"
            4 -> tv_search_goods_staramount.text = "4"
            5 -> tv_search_goods_staramount.text = "5"
            else -> tv_search_goods_staramount.text = "0"
        }
        when(foodData.avgPrefer.toInt()){
            1 -> tv_search_goods_heartamount.text = "1"
            2 -> tv_search_goods_heartamount.text = "2"
            3 -> tv_search_goods_heartamount.text = "3"
            4 -> tv_search_goods_heartamount.text = "4"
            5 -> tv_search_goods_heartamount.text = "5"
            else -> tv_search_goods_heartamount.text = "0"
        }
        foodIdx = foodData.foodIdx
        foodMeat = foodData.foodMeat
        foodDry = foodData.foodDry
        foodLink = foodData.foodLink
        reviewCount = foodData.reviewCount
        reviewIdx = foodData.reviewIdx
    }
}