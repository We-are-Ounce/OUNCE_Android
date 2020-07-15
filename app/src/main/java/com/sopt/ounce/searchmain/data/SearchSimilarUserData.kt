package com.sopt.ounce.searchmain.data

import java.io.Serializable

data class SearchSimilarUserData (
    var img_search_main_profile_src : String,
    var tv_search_main_cat_name_txt : String,
    var tv_search_main_cat_similarity_txt : Int,
    var img_search_main_review : ArrayList<String>,
    var profileIdx : Int
): Serializable