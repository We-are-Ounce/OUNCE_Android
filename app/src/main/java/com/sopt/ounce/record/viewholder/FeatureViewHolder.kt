package com.sopt.ounce.record.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sopt.ounce.R
import com.sopt.ounce.record.data.FeatureData

class FeatureViewHolder (view : View) : RecyclerView.ViewHolder(view){
    var feature : TextView = view.findViewById(R.id.txt_feature)

    fun onBind(data:FeatureData){
        feature.text = data.feature
    }
}