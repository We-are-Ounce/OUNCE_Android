# SearchView에 포커스를 할 시 하단 창을 변경해 봅시다

## SearchView에 포커스를 하는 이벤트를 받아들이는 리스너 : OnFocusChangeListener
### SearchFragment 중 일부
```
searchView.setOnQueryTextFocusChangeListener(object : View.OnFocusChangeListener{
    override fun onFocusChange(view: View?, hasFocus: Boolean) {
        if(hasFocus){//서치뷰에 포커스가 되어있을 경우
            //하단 창에서 포커스될 때 보여주고 싶은 레이아웃의 visibility를 변경한다
            clayout_search_main_notfocus.visibility = View.GONE
            clayout_search_main_focus.visibility = View.VISIBLE
        }
        //서치뷰에 포커스가 풀렸을 경우 키보드 Back 키에 맞춰서 화면을 변경해야하기 때문에
        //포커스 리스너에서 변경하지 않음
    }
})
```

내가 구현했던 방법은 서치뷰 하단에 두 개의 레이아웃을 놓고 포커스를 할 때와 안 할 때 visibility를 변경하는 식으로 두 페이지로 구성된 것처럼
한 레이아웃을 구성했다.
