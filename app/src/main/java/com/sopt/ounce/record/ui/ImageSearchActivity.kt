package com.sopt.ounce.record.ui

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.amn.easysharedpreferences.EasySharedPreference
import com.sopt.ounce.R
import com.sopt.ounce.record.adapter.ItemAdapter
import com.sopt.ounce.record.data.ItemData
import com.sopt.ounce.record.RecordItemDecoration
import com.sopt.ounce.record.adapter.RecyclerViewAdapter
import com.sopt.ounce.record.data.RecordSearchFoodData
import com.sopt.ounce.record.data.RequestFoodRecordData
import com.sopt.ounce.record.data.ResponseFoodRecordData
import com.sopt.ounce.searchmain.data.foodsearch.FoodData
import com.sopt.ounce.searchmain.data.foodsearch.RequestFoodSearchData
import com.sopt.ounce.server.OunceServiceImpl
import com.sopt.ounce.util.customEnqueue
import kotlinx.android.synthetic.main.activity_image_search.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Path

class ImageSearchActivity : AppCompatActivity() {

    private lateinit var adapter: RecyclerViewAdapter
    lateinit var mItemAdapter: ItemAdapter
    var mGoodsData = mutableListOf<RecordSearchFoodData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_search)

        val searchIcon = record_search_sv.findViewById<ImageView>(R.id.search_mag_icon)
        searchIcon.setColorFilter(Color.BLACK)


        val cancelIcon = record_search_sv.findViewById<ImageView>(R.id.search_close_btn)
        cancelIcon.setColorFilter(Color.GRAY)

9
        val textView = record_search_sv.findViewById<TextView>(R.id.search_src_text)
        textView.setTextColor(Color.DKGRAY)
        rv_record_search.layoutManager = LinearLayoutManager(rv_record_search.context)
        rv_record_search.setHasFixedSize(true)
        //getItemList()
        mItemAdapter = ItemAdapter(this)
        rv_record_item.adapter = mItemAdapter
        record_search_sv.setOnQueryTextFocusChangeListener(object : View.OnFocusChangeListener{
            override fun onFocusChange(view: View?, hasFocus: Boolean) {
                if(hasFocus){
                    rv_record_search.visibility = View.GONE
                    rv_record_item.visibility = View.VISIBLE
                }
            }

        })

        record_search_sv.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.d("Record - error", "requestBefore")
                val ounce = OunceServiceImpl.SERVICE.postRecordFoodSearch(
                    profileIdx = 1,
                    body = RequestFoodRecordData(
                        searchKeyword = query!!,
                        pageStart = "1",
                        pageEnd = "5"
                    )
                )
                Log.d("Record - error", "${ounce.request()}")
                Log.d("Record - error", "requestAfter")
                ounce.enqueue(object : Callback<ResponseFoodRecordData>{
                    override fun onFailure(call: Call<ResponseFoodRecordData>, t: Throwable) {
                        Log.d("Record - error", "${t.message}")
                    }

                    override fun onResponse(
                        call: Call<ResponseFoodRecordData>,
                        response: Response<ResponseFoodRecordData>
                    ) {
                        Log.d("Record - error", "response")
                        if(response.isSuccessful){
                            Log.d("Record - error", "responseSuccess")
                            val mItemData = response.body()!!.data
                            mItemAdapter.datas = mItemData as MutableList<RecordSearchFoodData>
                            mItemAdapter.notifyDataSetChanged()
                        }else{
                            Log.d("Record - error", "responseFailure")
                            Log.d("Record - error", "${response.errorBody().toString()}")
                            Toast.makeText(this@ImageSearchActivity, "통신 실패", Toast.LENGTH_SHORT).show()
                        }

//                        Log.d("Record - error", "responseSuccess")
//                        Log.d("Record - error", "${response.body()!!.data}")
//                        if(response != null){
//                            val mItemData = response.body()!!.data
//                            mItemAdapter.datas = mItemData as MutableList<RecordSearchFoodData>
//                            mItemAdapter.notifyDataSetChanged()
//                        }
//                        else{
//                            Toast.makeText(this@ImageSearchActivity, "리스폰스에 객체가 없어용", Toast.LENGTH_SHORT).show()
//                        }
                    }
                })
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
//        mItemAdapter = ItemAdapter(this)
//        rv_record_item.adapter = mItemAdapter
        //getGoodsList()
    }

//    private fun getItemList() {
//        val listItem = ArrayList<String>()
//        listItem.add("이현우")
//        listItem.add("안드")
//        listItem.add("빅보")
//        listItem.add("바보")
//        listItem.add("주예는")
//        listItem.add("귀여워")
//        listItem.add("깜찍이")
//        listItem.add("꾹구각가")
//        listItem.add("메롱")
//        listItem.add("끄아")
//        listItem.add("국국")
//        adapter = RecyclerViewAdapter(listItem)
//        rv_record_search.adapter = adapter
//    }

//    private fun getGoodsList(){
//        mGoodsData.apply {
//            add(
//                ItemData(
//                    cardview_image = R.drawable.img_card_cat,
//                    cardview_item = "내추럴 발란스",
//                    cardview_itemname = "제품 이름 "
//                )
//            )
//            add(
//                ItemData(
//                    cardview_image = R.drawable.img_card_cat,
//                    cardview_item = "내추럴 발란스",
//                    cardview_itemname = "제품 이름"
//                )
//            )
//            add(
//                ItemData(
//                    cardview_image = R.drawable.img_card_cat,
//                    cardview_item = "내추럴 발란스",
//                    cardview_itemname = "제품 이름"
//                )
//            )
//            add(
//                ItemData(
//                    cardview_image = R.drawable.img_card_cat,
//                    cardview_item = "내추럴 발란스",
//                    cardview_itemname = "제품 이름"
//                )
//            )
//            add(
//                ItemData(
//                    cardview_image = R.drawable.img_card_cat,
//                    cardview_item = "내추럴 발란스",
//                    cardview_itemname = "제품 이름"
//                )
//            )
//            add(
//                ItemData(
//                    cardview_image = R.drawable.img_card_cat,
//                    cardview_item = "내추럴 발란스",
//                    cardview_itemname = "제품 이름"
//                )
//            )
//            add(
//                ItemData(
//                    cardview_image = R.drawable.img_card_cat,
//                    cardview_item = "내추럴 발란스",
//                    cardview_itemname = "제품 이름"
//                )
//            )
//            add(
//                ItemData(
//                    cardview_image = R.drawable.img_card_cat,
//                    cardview_item = "내추럴 발란스",
//                    cardview_itemname = "제품 이름"
//                )
//            )
//        }
//        mItemAdapter.datas = mGoodsData
//        mItemAdapter.notifyDataSetChanged()
//
//    }
}