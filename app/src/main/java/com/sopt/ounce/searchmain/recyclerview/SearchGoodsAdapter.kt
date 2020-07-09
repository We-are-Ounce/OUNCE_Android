package com.sopt.ounce.searchmain.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sopt.ounce.R

class SearchGoodsAdapter(private val context: Context): RecyclerView.Adapter<SearchGoodsViewHolder>() {
    var datas = mutableListOf<SearchGoodsData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchGoodsViewHolder {
        val view = LayoutInflater.from(context).inflate(
            R.layout.item_search_main_goodssearch,
            parent,
            false)
        return SearchGoodsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: SearchGoodsViewHolder, position: Int) {
        holder.onBind(datas[position])
    }
}