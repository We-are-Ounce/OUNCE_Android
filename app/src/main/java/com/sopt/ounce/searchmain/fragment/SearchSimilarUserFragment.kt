package com.sopt.ounce.searchmain.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.sopt.ounce.R
import kotlinx.android.synthetic.main.item_searchmain_similar.*
import kotlinx.android.synthetic.main.item_searchmain_similar.view.*

class SearchSimilarUserFragment : Fragment(){

    var img_search_main_profile_src : String = ""
    var tv_search_main_cat_name_txt = ""
    var tv_search_main_cat_similarity_txt = 0
    var img_search_main_review = ArrayList<String>()
    var profileIdx : Int = -1
    lateinit var mView : View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.item_searchmain_similar, container, false)
        bindInfo(mView)
        return mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        img_search_main_profile.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                Toast.makeText(mView.context, "position: ${tv_search_main_cat_name_txt}", Toast.LENGTH_SHORT).show()
            }

        })

    }

    fun bindInfo(view : View){
//        view.img_search_main_profile.setImageResource(img_search_main_profile_src)
//        view.tv_search_main_cat_name.text = tv_search_main_cat_name_txt
//        view.tv_search_main_cat_similarity.text = tv_search_main_cat_similarity_txt
//        view.img_search_main_review_1.setImageResource(img_search_main_review_1_src)
//        view.img_search_main_review_2.setImageResource(img_search_main_review_2_src)
//        view.img_search_main_review_3.setImageResource(img_search_main_review_3_src)
        Glide.with(view).load(img_search_main_profile_src).into(view.img_search_main_profile)
        view.tv_search_main_cat_name.text = tv_search_main_cat_name_txt
        view.tv_search_main_cat_similarity.text = tv_search_main_cat_similarity_txt.toString() + "%"
        val imgIdx = img_search_main_review.size
        when(imgIdx){
            0 -> {
                view.img_search_main_review_1.setImageResource(R.drawable.img_card_cat)
                view.img_search_main_review_2.setImageResource(R.drawable.img_card_cat)
                view.img_search_main_review_3.setImageResource(R.drawable.img_card_cat)
            }
            1 -> {
                Glide.with(view).load(img_search_main_review.get(0)).into(view.img_search_main_review_1)
                view.img_search_main_review_2.setImageResource(R.drawable.img_card_cat)
                view.img_search_main_review_3.setImageResource(R.drawable.img_card_cat)
            }
            2 -> {
                Glide.with(view).load(img_search_main_review.get(0)).into(view.img_search_main_review_1)
                Glide.with(view).load(img_search_main_review.get(1)).into(view.img_search_main_review_2)
                view.img_search_main_review_3.setImageResource(R.drawable.img_card_cat)
            }
            else -> {
                Glide.with(view).load(img_search_main_review.get(0)).into(view.img_search_main_review_1)
                Glide.with(view).load(img_search_main_review.get(1)).into(view.img_search_main_review_2)
                Glide.with(view).load(img_search_main_review.get(2)).into(view.img_search_main_review_3)
            }
        }


    }

}