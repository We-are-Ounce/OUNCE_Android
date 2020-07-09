package com.sopt.ounce.searchmain.recyclerview

import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import androidx.recyclerview.widget.RecyclerView


class SearchUserItemDecoration : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.bottom = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            22F,
            view.context.resources.displayMetrics).toInt()
    }
}