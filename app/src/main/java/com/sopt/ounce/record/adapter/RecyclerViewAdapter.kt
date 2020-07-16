package com.sopt.ounce.record.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.sopt.ounce.R
import com.sopt.ounce.record.ui.RecordActivity
import kotlinx.android.synthetic.main.record_search.view.*

class RecyclerViewAdapter(private var arrayList: ArrayList<String>):
    RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    var filterList = ArrayList<String>()
    init{
        filterList = arrayList
    }

    lateinit var mContext: Context

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    }

    interface OnItemClickListener{
        fun onItemClick(v : View, position: Int)
    }

    private var listener : OnItemClickListener? = null

    fun setOnItemClickListener (listener : OnItemClickListener){
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemListView =
            LayoutInflater.from(parent.context).inflate(R.layout.record_search, parent, false)
        val itemViewHolder =
            ItemViewHolder(
                itemListView
            )
        mContext = parent.context
        return itemViewHolder
    }

    override fun getItemCount(): Int {
        return filterList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.tv_search.text = filterList[position]
        holder.itemView.tv_search.setTextColor(Color.BLACK)
        holder.itemView.item_search.setBackgroundColor(Color.TRANSPARENT)
        holder.itemView.item_search.img_delete.setOnClickListener {
            if(arrayList.isNotEmpty()){
                arrayList.removeAt(position)
                notifyDataSetChanged()
            }
            else
                Toast.makeText(mContext, "리스트에 원소가 없습니다", Toast.LENGTH_SHORT).show()
        }
    }
}