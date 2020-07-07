# Fragment에 ViewPager를 달아봅시다

## Fragment에 Context를 어디서 가져올까?

답은 바로 View에서 가져오면 됩니다!

그런데 이런 Context는 View에서 가져와야하기 때문에 View가 만들어지고 Context를 가져와야겠죠 그래서 **onViewCreated에서 ViewPager를 제작**해야합니다.

### ViewPager를 onViewCreated에 부착합시다

```kotlin
override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //view에서 context를 가져옵니다
        var viewPagerAdapter = ViewPagerAdapter(view.context)
        /*
  			Adapter에 데이터 넣는 부분
        */
        vp_search_main_viewpager.adapter = viewPagerAdapter
        vp_search_main_viewpager.clipToPadding = false
        vp_search_main_viewpager.clipChildren = false
        vp_search_main_viewpager.offscreenPageLimit = 2
}
```

위와 같이 ViewPager의 Adapter를 설정하려면 Context를 가져와야 하는데 이를 위에서 제시한 방법대로 하면 Fragment 안에서도 ViewPager를 부착할 수 있습니다.