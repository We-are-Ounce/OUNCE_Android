package com.sopt.ounce.main.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sopt.ounce.R
import com.sopt.ounce.main.data.BottomProfileData
import de.hdodenhof.circleimageview.CircleImageView

class BottomProfileViewHolder ( view : View) : RecyclerView.ViewHolder(view){

    private val profile  = view.findViewById<ImageView>(R.id.img_bottomsheet_profile)
    private val name : TextView = view.findViewById(R.id.txt_bottomsheet_name)
    private val intro : TextView = view.findViewById(R.id.txt_bottomsheet_intro)

    fun onBind(data : BottomProfileData){

        Glide.with(itemView)
            .load(data.profile)
            .error(R.drawable.img_cat)
            .into(profile)

        name.text = data.name
        intro.text = data.introduce

    }
}