package com.sopt.ounce.record.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sopt.ounce.R
import com.sopt.ounce.record.data.FeatureData
import com.sopt.ounce.record.viewholder.FeatureViewHolder
import kotlinx.android.synthetic.main.activity_record.view.*
import java.util.zip.Inflater

class FeatureAdapter (private val context : Context) : RecyclerView.Adapter<FeatureViewHolder>(){

    var data = listOf<FeatureData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeatureViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rcv_record_item, parent,
            false)

        return FeatureViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: FeatureViewHolder, position: Int) {
        holder.onBind(data[position])
    }
}