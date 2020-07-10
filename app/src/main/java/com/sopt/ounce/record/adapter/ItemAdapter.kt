package com.sopt.ounce.record.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sopt.ounce.R
import com.sopt.ounce.record.ItemData
import com.sopt.ounce.record.ItemViewHolder

class ItemAdapter(private val context: Context) : RecyclerView.Adapter<ItemViewHolder>() {
    var datas = mutableListOf<ItemData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(context).inflate(
            R.layout.record_search_cardview,
            parent,
            false)
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.onBind(datas[position])
    }
}

