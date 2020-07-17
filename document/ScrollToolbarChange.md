# 상단으로 스크롤할 때 툴바를 변경해봅시다(ProductReviewActivity)
## Coordinator Layout을 활용해 스크롤 시 툴바를 변경해봅시다
### CollapsingToolbarLayout에 RelativeLayout을 넣어야 동적으로 레이아웃이 변경이 됩니다
CollapsingToolbarLayout에 ConstraintLayout을 활용하여 Toolbar를 구성할 경우, 레이아웃이 잘 변경되지 않는 문제점이 발생함
따라서, CollapsingToolbarLayout 내에 있는 ViewGroup을 RelativeLayout으로 적용하여 효과 구현

### 하나의 툴바에서 두 개의 레이아웃을 구성하여 교체
두 개의 레이아웃(```tbar_product_review_bottom```, ```tbar_product_review_top```)을 구성한다.
수직 스크롤 변화를 감지하는 리스너(OnOffsetChangedListener)를 활용하여 일정 이상 스크롤이 된다면 
visibility가 gone이었던 레이아웃의 visibilty를 visible로 변경하고
visibility가 visibility이었던 레이아웃의 visibilty를 gone으로 변경한다.

### 스크롤을 내리면서 alpha 값을 조정하여 애니메이션 구현
```
override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
    if(verticalOffset > {일정 값}) {
        tbar_product_review_top.visibility = View.VISIBLE
        tbar_product_review_top.alpha = ({일정 값} - abs(verticalOffset.toFloat()))/{일정 값}
    }
    else {
        tbar_product_review_top.visibility = View.GONE
        tbar_product_review_bottom.visibility = View.VISIBLE
    }
}
```
tbar_product_review_top.alpha = ({일정 값} - abs(verticalOffset.toFloat()))/{일정 값}을 활용하여
레이아웃의 투명도 조정, 내려가면서 해당 레이아웃이 투명해지면서 일정 스크롤이 지날 때 하단 툴바가 생성되는 듯한 애니메이션 구현
