package com.sopt.ounce.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sopt.ounce.R
import com.sopt.ounce.main.data.ResponseMainReviewData
import com.sopt.ounce.main.viewholder.ReviewViewHolder

class ReviewAdapter (private val context :Context) : RecyclerView.Adapter<ReviewViewHolder>(){

    var data = arrayListOf<ResponseMainReviewData.Data>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.main_review_list_item,
                parent, false)
        return  ReviewViewHolder(view)


    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        holder.onBind(data[position])
    }
}