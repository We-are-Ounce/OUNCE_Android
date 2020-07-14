package com.sopt.ounce.searchmain.data

import java.io.Serializable

data class SearchSimilarUserData (
    var img_search_main_profile_src : Int,
    var tv_search_main_cat_name_txt : String,
    var tv_search_main_cat_similarity_txt : String,
    var img_search_main_review_1_src : Int,
    var img_search_main_review_2_src : Int,
    var img_search_main_review_3_src : Int
): Serializable