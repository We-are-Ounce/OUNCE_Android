package com.sopt.ounce.main.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sopt.ounce.R
import kotlinx.android.synthetic.main.fragment_other_user.*
import kotlinx.android.synthetic.main.fragment_other_user.view.*


class OtherUserFragment : Fragment() {

    private lateinit var mContext : Context
    private lateinit var v : View


    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_other_user, container, false)
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_other_follow.setOnClickListener {
            if(it.isSelected){
                // true -> false로 가니까
                btn_other_follow.text = "팔로우"
            }
            else{
                //false -> true 가니까
                btn_other_follow.text = "팔로우 취소"
            }

            it.isSelected = !it.isSelected
        }
    }


}