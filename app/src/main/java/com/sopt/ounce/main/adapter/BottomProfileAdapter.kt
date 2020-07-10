package com.sopt.ounce.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sopt.ounce.R
import com.sopt.ounce.main.data.BottomProfileData
import com.sopt.ounce.main.viewholder.BottomProfileViewHolder

class BottomProfileAdapter (private val context : Context) : RecyclerView.Adapter<BottomProfileViewHolder>(){

    var data = listOf<BottomProfileData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BottomProfileViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.bottom_profile_item, parent, false)
        return BottomProfileViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: BottomProfileViewHolder, position: Int) {
        holder.onBind(data[position])
    }
}