# 뷰페이저로 화면전환 할 때 에니메이션 넣기

## 구현 화면

<img src = "https://user-images.githubusercontent.com/54518925/86801141-ae56fb80-c0ae-11ea-8b29-4b68fded4097.gif" width = 30%/>

## 구현 방법

### 레이아웃에 뷰페이저 장착

```groovy
<androidx.viewpager.widget.ViewPager
        android:id="@+id/vp_search_main_viewpager"
        android:layout_width="0dp"
        android:layout_height="0dp"
        android:layout_marginVertical="40dp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/linlo_explain" />
```

### 어댑터(ViewPagerAdapter)에 부착

```kotlin
class ViewPagerAdapter(val context: Context): PagerAdapter() {
    var img_search_main_profile_src = listOf<Int>()
    var tv_search_main_cat_name_txt = listOf<String>()
    var tv_search_main_cat_similarity_txt = listOf<String>()
    var img_search_main_review_1_src = listOf<Int>()
    var img_search_main_review_2_src = listOf<Int>()
    var img_search_main_review_3_src = listOf<Int>()

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        var inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var itemView = inflater.inflate(R.layout.item_searchmain_similar, container, false)
        val img_search_main_profile = itemView.findViewById<ImageView>(R.id.img_search_main_profile)
        val tv_search_main_cat_name = itemView.findViewById<TextView>(R.id.tv_search_main_cat_name)
        val tv_search_main_cat_similarity = itemView.findViewById<TextView>(R.id.tv_search_main_cat_similarity)
        val img_search_main_review_1 = itemView.findViewById<ImageView>(R.id.img_search_main_review_1)
        val img_search_main_review_2 = itemView.findViewById<ImageView>(R.id.img_search_main_review_2)
        val img_search_main_review_3 = itemView.findViewById<ImageView>(R.id.img_search_main_review_3)
        img_search_main_profile.setImageResource(img_search_main_profile_src[position])
        tv_search_main_cat_name.text = tv_search_main_cat_name_txt[position]
        tv_search_main_cat_similarity.text = "${tv_search_main_cat_similarity_txt[position]}%"
        img_search_main_review_1.setImageResource(img_search_main_review_1_src[position])
        img_search_main_review_2.setImageResource(img_search_main_review_2_src[position])
        img_search_main_review_3.setImageResource(img_search_main_review_3_src[position])
        container.addView(itemView)
        return itemView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return img_search_main_profile_src.size
    }
}
```

### 커스텀 PageTransformer 클래스 제작

```kotlin
class ViewPagerTransformer : ViewPager.PageTransformer{
    override fun transformPage(page: View, position: Float) {
        val r = 1 - abs(position)

        page.scaleY = (0.85F + r * 0.15F)
    }
}
```

transformPage는 화면 정중앙을 0으로 기준 삼고 화면상 최고 좌측 부분은 -1/최고 우측 부분은 1로 position을 설정한다. 대칭인 부분을 기준으로 `1 -abs(position)`은 값이 같아 이를 활용하여 `page.scaleY`를 설정하여 화면이 넘어갈 때 자연스럽게 크기 조절을 유도한다

### ViewPager에 장착하기

```kotlin
vp_search_main_viewpager.setPageTransformer(true, ViewPagerTransformer())

val dpValue = 70
val d = resources.displayMetrics.density
val margin = dpValue*d.toInt()

vp_search_main_viewpager.setPadding(margin,0,margin,0)
vp_search_main_viewpager.pageMargin = margin/2
```

뷰페이저에 PageTransformer를 장착하고 임의의 padding 값을 주어 중앙 page와 주변 page 간 padding을 설정한다.

이때, offPageScreenLimit은 스크린을 많이 생성할 경우 중앙 스크린이 중앙에 위치하지 않을 수 있으므로 1 혹은 2로 설정한다.