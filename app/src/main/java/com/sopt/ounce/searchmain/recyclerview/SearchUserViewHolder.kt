package com.sopt.ounce.searchmain.recyclerview

import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sopt.ounce.R
import com.sopt.ounce.main.ui.OtherActivity
import com.sopt.ounce.searchmain.data.usersearch.UserData
import kotlinx.android.synthetic.main.item_search_main_usersearch.view.*

class SearchUserViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
    val img_search_user_catimage = itemView.findViewById<ImageView>(R.id.img_search_user_catimage)
    val tv_search_user_id = itemView.findViewById<TextView>(R.id.tv_search_user_id)
    val tv_search_user_cat = itemView.findViewById<TextView>(R.id.tv_search_user_cat)
    val tv_search_user_explain = itemView.findViewById<TextView>(R.id.tv_search_user_explain)
    var userIdx = 0
    var profileIdx = 0
    fun onBind(userData: UserData){
//        img_search_user_catimage.setImageResource(searchUserData.img_search_user_catimage)
//        tv_search_user_id.text = searchUserData.tv_search_user_id
//        tv_search_user_cat.text = searchUserData.tv_search_user_cat
//        tv_search_user_explain.text = searchUserData.tv_search_user_explain
        Glide.with(itemView).load(userData.profileImg).into(img_search_user_catimage)
        tv_search_user_id.text = userData.id
        tv_search_user_cat.text = userData.profileName
        tv_search_user_explain.text = userData.profileInfo
        userIdx = userData.userIdx
        profileIdx = userData.profileIdx
        img_search_user_catimage.setOnClickListener(object : View.OnClickListener{
            override fun onClick(view: View?) {
                val intent = Intent(view!!.context, OtherActivity::class.java)
                intent.putExtra("otherProfile", profileIdx)
                view!!.context.startActivity(intent)
            }

        })

    }
}