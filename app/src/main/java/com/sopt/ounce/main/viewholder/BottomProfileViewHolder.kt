package com.sopt.ounce.main.viewholder

import android.os.SystemClock
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sopt.ounce.R
import com.sopt.ounce.main.adapter.BottomProfileAdapter
import com.sopt.ounce.main.data.BottomProfileData
import de.hdodenhof.circleimageview.CircleImageView

class BottomProfileViewHolder ( view : View) : RecyclerView.ViewHolder(view){

    private val profile  = view.findViewById<ImageView>(R.id.img_bottomsheet_profile)
    private val name : TextView = view.findViewById(R.id.txt_bottomsheet_name)
    private val intro : TextView = view.findViewById(R.id.txt_bottomsheet_intro)
    private var mLastClickTime : Long = 0

    fun onBind(data : BottomProfileData.Data,
    listener : BottomProfileAdapter.OnItemClickListener?){

        Glide.with(itemView)
            .load(data.profileImg)
            .error(R.drawable.img_cat)
            .into(profile)

        name.text = data.profileName
        intro.text = data.profileInfo

        itemView.setOnClickListener {
            if(SystemClock.elapsedRealtime() - mLastClickTime > 1000){
                val pos = adapterPosition
                if( pos != RecyclerView.NO_POSITION){
                    listener?.onItemClick(itemView, data)
                }
            }
            mLastClickTime = SystemClock.elapsedRealtime()
        }

    }
}