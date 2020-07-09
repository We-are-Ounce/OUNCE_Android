package com.sopt.ounce.searchmain.recyclerview

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sopt.ounce.R

class SearchGoodsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val img_search_goods_goodsimage = itemView.findViewById<ImageView>(
        R.id.img_search_goods_goodsimage)
    val tv_search_goods_company = itemView.findViewById<TextView>(R.id.tv_search_goods_company)
    val tv_search_goods_name = itemView.findViewById<TextView>(R.id.tv_search_goods_name)
    val tv_search_goods_review = itemView.findViewById<TextView>(R.id.tv_search_goods_review)
    val tv_search_goods_staramount = itemView.findViewById<TextView>(R.id.tv_search_goods_staramount)
    val tv_search_goods_heartamount = itemView.findViewById<TextView>(R.id.tv_search_goods_heartamount)

    fun onBind(searchGoodsData: SearchGoodsData){
        img_search_goods_goodsimage.setImageResource(searchGoodsData.img_search_goods_goodsimage)
        tv_search_goods_company.text = searchGoodsData.tv_search_goods_company
        tv_search_goods_name.text = searchGoodsData.tv_search_goods_name
        tv_search_goods_review.text = searchGoodsData.tv_search_goods_review
        tv_search_goods_staramount.text = searchGoodsData.tv_search_goods_staramount
        tv_search_goods_heartamount.text = searchGoodsData.tv_search_goods_heartamount
    }
}