# 검색창에서 검색을 할 때 하단 창에서 검색결과 보여주기
## 검색 뷰 구조
SearchFragment(검색 창이 있는 Fragment) 내부 하단에 검색 결과 창들이 있는 TabLayout이 포함된 프래그먼트가 존재함
+ SearchFragment 내부에 다른 프래그먼트가 있다
+ 한 프래그먼트에서 검색을 할 때 다른 프래그먼트 리스트 뷰에 해당 쿼리에 대한 검색 결과를 보여줘야 됨

## 동일한 액티비티 내에서 한 프래그먼트에서 다른 프래그먼트의 뷰를 접근해야할 때 : Activity를 통해 접근
SearchFragment.kt 중 일부
```
val mActivity = activity as MainActivity
val searchUserRecyclerView = mActivity.findViewById<RecyclerView>(R.id.rv_search_user_searchresult)                            
```
두 프래먼트가 동일 액티비티에 있을 때, 부모 액티비티에서 접근하여 다른 프래그먼트의 뷰를 ```findViewById```로 가져온다

## 동일한 액티비티 내에서 한 프래그먼트에서 다른 프래그먼트의 변수(데이터)를 전달해야할 때 : ViewModeld을 활용하여 전달
SearchFragment.kt 중 일부
```
val mViewModel = ViewModelProviders.of(activity as MainActivity).get(SpinnerAdapterViewModel::class.java)
mViewModel.userQuery == query!!                            
```
SpinnerAdapterViewModel 중 일부
```
class SpinnerAdapterViewModel : ViewModel() {
    var productQuery : String = ""
    var userQuery : String = ""
}
```
ViewModel은 특정 창에 종속되지 않고 데이터를 전달하기 위해 만들었다. 이를 통해서 검색창에서 query를 뷰모델에 저장하고
이를 검색 창 프래그먼트에 전달해줘서 서버 통신을 하여 검색 결과에 대한 RecyclerView를 구성한다.
