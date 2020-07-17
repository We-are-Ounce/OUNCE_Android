package com.sopt.ounce.searchmain.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.sopt.ounce.R
import com.sopt.ounce.main.ui.OtherActivity
import com.sopt.ounce.productreview.ProductReviewActivity
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

//        img_search_main_profile.setOnClickListener(object : View.OnClickListener{
//            override fun onClick(view: View?) {
//                //Toast.makeText(mView.context, "profileIdx: ${profileIdx}", Toast.LENGTH_SHORT).show()
//                val intent = Intent(mView.context, OtherActivity::class.java)
//                intent.putExtra("otherProfile", profileIdx)
//                mView.context.startActivity(intent)
//            }
//        })

    }

    fun bindInfo(view : View){
        Glide.with(view).load(img_search_main_profile_src).into(view.img_search_main_profile)
        view.tv_search_main_cat_name.text = tv_search_main_cat_name_txt
        view.tv_search_main_cat_similarity.text = tv_search_main_cat_similarity_txt.toString() + "%"
        view.setOnClickListener(object : View.OnClickListener{
            override fun onClick(view: View?) {
                //Toast.makeText(mView.context, "profileIdx: ${profileIdx}", Toast.LENGTH_SHORT).show()
                val intent = Intent(mView.context, OtherActivity::class.java)
                intent.putExtra("otherProfile", profileIdx)
                mView.context.startActivity(intent)
            }
        })
        val imgIdx = img_search_main_review.size
        when(imgIdx){
            0 -> {
                view.img_search_main_review_1.setImageResource(R.drawable.ic_empty)
                view.img_search_main_review_2.setImageResource(R.drawable.ic_empty)
                view.img_search_main_review_3.setImageResource(R.drawable.ic_empty)
            }
            1 -> {
                Glide.with(view).load(img_search_main_review.get(0)).into(view.img_search_main_review_1)
                view.img_search_main_review_2.setImageResource(R.drawable.ic_empty)
                view.img_search_main_review_3.setImageResource(R.drawable.ic_empty)
            }
            2 -> {
                Glide.with(view).load(img_search_main_review.get(0)).into(view.img_search_main_review_1)
                Glide.with(view).load(img_search_main_review.get(1)).into(view.img_search_main_review_2)
                view.img_search_main_review_3.setImageResource(R.drawable.ic_empty)
            }
            else -> {
                Glide.with(view).load(img_search_main_review.get(0)).into(view.img_search_main_review_1)
                Glide.with(view).load(img_search_main_review.get(1)).into(view.img_search_main_review_2)
                Glide.with(view).load(img_search_main_review.get(2)).into(view.img_search_main_review_3)
            }
        }


    }

}