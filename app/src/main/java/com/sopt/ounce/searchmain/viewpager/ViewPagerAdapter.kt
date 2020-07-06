package com.sopt.ounce.searchmain.viewpager

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.sopt.ounce.R

class ViewPagerAdapter(val context: Context): PagerAdapter() {
    var img_search_main_profile_src = listOf<Int>()
    var tv_search_main_cat_name_txt = listOf<String>()
    var tv_search_main_cat_similarity_txt = listOf<String>()
    var img_search_main_review_1_src = listOf<Int>()
    var img_search_main_review_2_src = listOf<Int>()
    var img_search_main_review_3_src = listOf<Int>()

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        var inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var itemView = inflater.inflate(R.layout.item_searchmain_similar, container, false)
        val img_search_main_profile = itemView.findViewById<ImageView>(R.id.img_search_main_profile)
        val tv_search_main_cat_name = itemView.findViewById<TextView>(R.id.tv_search_main_cat_name)
        val tv_search_main_cat_similarity = itemView.findViewById<TextView>(R.id.tv_search_main_cat_similarity)
        val img_search_main_review_1 = itemView.findViewById<ImageView>(R.id.img_search_main_review_1)
        val img_search_main_review_2 = itemView.findViewById<ImageView>(R.id.img_search_main_review_2)
        val img_search_main_review_3 = itemView.findViewById<ImageView>(R.id.img_search_main_review_3)
        img_search_main_profile.setImageResource(img_search_main_profile_src[position])
        tv_search_main_cat_name.text = tv_search_main_cat_name_txt[position]
        tv_search_main_cat_similarity.text = "${tv_search_main_cat_similarity_txt[position]}%"
        img_search_main_review_1.setImageResource(img_search_main_review_1_src[position])
        img_search_main_review_2.setImageResource(img_search_main_review_2_src[position])
        img_search_main_review_3.setImageResource(img_search_main_review_3_src[position])
        container.addView(itemView)
        return itemView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return img_search_main_profile_src.size
    }

//    private fun bindItem(itemView: View){
//        val img_search_main_profile = itemView.findViewById<ImageView>(R.id.img_search_main_profile)
//        val tv_search_main_cat_name = itemView.findViewById<TextView>(R.id.tv_search_main_cat_name)
//        val tv_search_main_cat_similarity = itemView.findViewById<TextView>(R.id.tv_search_main_cat_similarity)
//        val img_search_main_review_1 = itemView.findViewById<ImageView>(R.id.img_search_main_review_1)
//        val img_search_main_review_2 = itemView.findViewById<ImageView>(R.id.img_search_main_review_2)
//        val img_search_main_review_3 = itemView.findViewById<ImageView>(R.id.img_search_main_review_3)
//        img_search_main_profile.setImageResource(img_search_main_profile_src)
//        tv_search_main_cat_name.text = data.tv_search_main_cat_name
//        tv_search_main_cat_similarity.text = "${data.tv_search_main_cat_similarity}%"
//        img_search_main_review_1.setImageResource(data.img_search_main_review_1)
//        img_search_main_review_2.setImageResource(data.img_search_main_review_2)
//        img_search_main_review_3.setImageResource(data.img_search_main_review_3)
//    }
}