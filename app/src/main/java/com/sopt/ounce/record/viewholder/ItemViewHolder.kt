package com.sopt.ounce.record.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sopt.ounce.R
import com.sopt.ounce.record.data.ItemData

class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val cardview_image = itemView.findViewById<ImageView>(R.id.cardview_image)
    val cardview_item = itemView.findViewById<TextView>(R.id.cardview_item)
    val cardview_itemname = itemView.findViewById<TextView>(R.id.cardview_itemname)


    fun onBind(itemData: ItemData){
        cardview_image.setImageResource(itemData.cardview_image)
        cardview_item.text = itemData.cardview_item
        cardview_itemname.text = itemData.cardview_itemname

    }
}