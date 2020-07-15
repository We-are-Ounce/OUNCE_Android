package com.sopt.ounce.record.viewholder

import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sopt.ounce.R
import com.sopt.ounce.record.data.ItemData
import com.sopt.ounce.record.ui.RecordActivity
import com.sopt.ounce.searchmain.data.foodsearch.FoodData

class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val cardview_image = itemView.findViewById<ImageView>(R.id.cardview_image)
    val cardview_item = itemView.findViewById<TextView>(R.id.cardview_item)
    val cardview_itemname = itemView.findViewById<TextView>(R.id.cardview_itemname)

    fun onBind(foodData: FoodData){
        Glide.with(itemView).load(foodData.foodImg).into(cardview_image)
        cardview_itemname.text = foodData.foodManu
        cardview_item.text = foodData.foodName

        itemView.setOnClickListener {
            val intent = Intent(itemView.context, RecordActivity::class.java)
            intent.putExtra("foodItem",foodData)
            itemView.context.startActivity(intent)
        }
    }
}