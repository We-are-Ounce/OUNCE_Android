package com.sopt.ounce.searchmain.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import com.sopt.ounce.R
import com.sopt.ounce.main.ui.MainActivity
import com.sopt.ounce.searchmain.recyclerview.SearchUserAdapter
import com.sopt.ounce.searchmain.recyclerview.SearchUserData
import com.sopt.ounce.searchmain.recyclerview.SearchUserItemDecoration
import gun0912.tedkeyboardobserver.TedKeyboardObserver
import kotlinx.android.synthetic.main.fragment_search.view.*
import kotlinx.android.synthetic.main.fragment_search_user.*

class SearchUserFragment : Fragment() {
    lateinit var searchUserAdapter : SearchUserAdapter
    private lateinit var mInputMethodManager: InputMethodManager
    private lateinit var mContext: Context
    private lateinit var mView: View
    private lateinit var iView : View
    var mUserDatas = mutableListOf<SearchUserData>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_search_user, container, false)
        iView = inflater.inflate(R.layout.fragment_search, container, false)
        observeKeyboard()
        settingMethodManager()
        return mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchUserAdapter = SearchUserAdapter(view.context)
        rv_search_user_searchresult.adapter = searchUserAdapter
        loadUserResultData()
        rv_search_user_searchresult.addItemDecoration(SearchUserItemDecoration())
    }

    private fun loadUserResultData(){
        mUserDatas.apply {
            add(
                SearchUserData(
                    img_search_user_catimage = R.drawable.img_card_cat,
                    tv_search_user_cat = "Spring",
                    tv_search_user_id = "@ounceHyunWoo",
                    tv_search_user_explain = "My First Cat"
                )
            )
            add(
                SearchUserData(
                    img_search_user_catimage = R.drawable.img_card_cat,
                    tv_search_user_cat = "Summer",
                    tv_search_user_id = "@ounceHyunWoo",
                    tv_search_user_explain = "My Second Cat"
                )
            )
            add(
                SearchUserData(
                    img_search_user_catimage = R.drawable.img_card_cat,
                    tv_search_user_cat = "Autumn",
                    tv_search_user_id = "@ounceHyunWoo",
                    tv_search_user_explain = "My Third Cat"
                )
            )
            add(
                SearchUserData(
                    img_search_user_catimage = R.drawable.img_card_cat,
                    tv_search_user_cat = "Winter",
                    tv_search_user_id = "@ounceHyunWoo",
                    tv_search_user_explain = "My Fourth Cat"
                )
            )
            add(
                SearchUserData(
                    img_search_user_catimage = R.drawable.img_card_cat,
                    tv_search_user_cat = "Winter",
                    tv_search_user_id = "@ounceHyunWoo",
                    tv_search_user_explain = "My Fourth Cat"
                )
            )
            add(
                SearchUserData(
                    img_search_user_catimage = R.drawable.img_card_cat,
                    tv_search_user_cat = "Winter",
                    tv_search_user_id = "@ounceHyunWoo",
                    tv_search_user_explain = "My Fourth Cat"
                )
            )
            add(
                SearchUserData(
                    img_search_user_catimage = R.drawable.img_card_cat,
                    tv_search_user_cat = "Winter",
                    tv_search_user_id = "@ounceHyunWoo",
                    tv_search_user_explain = "My Fourth Cat"
                )
            )
            add(
                SearchUserData(
                    img_search_user_catimage = R.drawable.img_card_cat,
                    tv_search_user_cat = "Winter",
                    tv_search_user_id = "@ounceHyunWoo",
                    tv_search_user_explain = "My Fourth Cat"
                )
            )
            add(
                SearchUserData(
                    img_search_user_catimage = R.drawable.img_card_cat,
                    tv_search_user_cat = "Winter",
                    tv_search_user_id = "@ounceHyunWoo",
                    tv_search_user_explain = "My Fourth Cat"
                )
            )
            add(
                SearchUserData(
                    img_search_user_catimage = R.drawable.img_card_cat,
                    tv_search_user_cat = "Winter",
                    tv_search_user_id = "@ounceHyunWoo",
                    tv_search_user_explain = "My Fourth Cat"
                )
            )
        }
        searchUserAdapter.datas = mUserDatas
        searchUserAdapter.notifyDataSetChanged()
    }

    private fun observeKeyboard(){
        activity?.let{
            TedKeyboardObserver(it)
                .listen { isShow ->
                    if (!isShow){
                        iView.sv_search_main_search.clearFocus()
                    }

                }
        }
    }

    private fun settingMethodManager(){
        val activity = activity as MainActivity
        mInputMethodManager = activity.methodManagerToFragment()
    }

}