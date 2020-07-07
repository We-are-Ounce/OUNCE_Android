package com.sopt.ounce.searchmain.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sopt.ounce.R

class SearchUserAdapter(private val context: Context): RecyclerView.Adapter<SearchUserViewHolder>() {
    var datas = mutableListOf<SearchUserData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchUserViewHolder {
        val view = LayoutInflater.from(context).inflate(
            R.layout.item_search_main_usersearch,
            parent,
        false)
        return SearchUserViewHolder(view)
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: SearchUserViewHolder, position: Int) {
        holder.onBind(datas[position])
    }
}