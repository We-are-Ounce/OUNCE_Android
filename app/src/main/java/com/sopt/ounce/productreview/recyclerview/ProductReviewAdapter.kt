package com.sopt.ounce.productreview.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sopt.ounce.R
import com.sopt.ounce.searchmain.data.showreview.ReviewData
import com.sopt.ounce.searchmain.recyclerview.SearchGoodsViewHolder

class ProductReviewAdapter(private val context: Context): RecyclerView.Adapter<ProductReviewViewHolder>() {
    var datas = listOf<ReviewData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductReviewViewHolder {
        val view = LayoutInflater.from(context).inflate(
            R.layout.item_product_review_review,
            parent,
            false)
        return ProductReviewViewHolder(view)
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: ProductReviewViewHolder, position: Int) {
        holder.onBind(datas[position])
    }
}