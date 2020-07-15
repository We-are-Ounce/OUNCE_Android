package com.sopt.ounce.searchmain.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import com.sopt.ounce.R
import com.sopt.ounce.main.ui.MainActivity
import com.sopt.ounce.searchmain.data.usersearch.ResponseUserSearchData
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
    lateinit var mUserSearchData : ResponseUserSearchData

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Search - Create", "create")
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
        Log.d("Search - CreateView", "CreateViewBefore")
        return mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("Search - afterViewCreated", "afterViewCreated")
        searchUserAdapter = SearchUserAdapter(mView.context)
        rv_search_user_searchresult.adapter = searchUserAdapter
        Log.d("Search - after", "data set")
        rv_search_user_searchresult.addItemDecoration(SearchUserItemDecoration())
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