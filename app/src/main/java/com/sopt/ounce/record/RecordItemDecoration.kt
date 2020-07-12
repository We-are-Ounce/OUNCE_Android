package com.sopt.ounce.record

import android.content.Context
import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class RecordItemDecoration(context : Context, size : Int = 6): RecyclerView.ItemDecoration() {
    private val size_space : Int

    init{
        size_space = dpToPx(context,size)
    }

    private fun dpToPx(context : Context, dp : Int) : Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), context.resources.displayMetrics).toInt()

    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        if(parent.getChildAdapterPosition(view) != state.itemCount - 1){
            outRect.right = size_space
        }
    }
}





