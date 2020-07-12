package com.sopt.ounce.searchmain.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sopt.ounce.R
import kotlinx.android.synthetic.main.item_searchmain_similar.view.*

class SearchSimilarUserFragment : Fragment() {

    var img_search_main_profile_src = 0
    var tv_search_main_cat_name_txt = ""
    var tv_search_main_cat_similarity_txt = ""
    var img_search_main_review_1_src = 0
    var img_search_main_review_2_src = 0
    var img_search_main_review_3_src = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.item_searchmain_similar, container, false)
        bindInfo(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    fun bindInfo(view : View){
        view.img_search_main_profile.setImageResource(img_search_main_profile_src)
        view.tv_search_main_cat_name.text = tv_search_main_cat_name_txt
        view.tv_search_main_cat_similarity.text = tv_search_main_cat_similarity_txt
        view.img_search_main_review_1.setImageResource(img_search_main_review_1_src)
        view.img_search_main_review_2.setImageResource(img_search_main_review_2_src)
        view.img_search_main_review_3.setImageResource(img_search_main_review_3_src)
    }
}