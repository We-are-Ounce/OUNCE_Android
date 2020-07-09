package com.sopt.ounce.searchmain.recyclerview

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sopt.ounce.R
import kotlinx.android.synthetic.main.item_search_main_usersearch.view.*

class SearchUserViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
    val img_search_user_catimage = itemView.findViewById<ImageView>(R.id.img_search_user_catimage)
    val tv_search_user_id = itemView.findViewById<TextView>(R.id.tv_search_user_id)
    val tv_search_user_cat = itemView.findViewById<TextView>(R.id.tv_search_user_cat)
    val tv_search_user_explain = itemView.findViewById<TextView>(R.id.tv_search_user_explain)
    fun onBind(searchUserData: SearchUserData){
        img_search_user_catimage.setImageResource(searchUserData.img_search_user_catimage)
        tv_search_user_id.text = searchUserData.tv_search_user_id
        tv_search_user_cat.text = searchUserData.tv_search_user_cat
        tv_search_user_explain.text = searchUserData.tv_search_user_explain
    }
}