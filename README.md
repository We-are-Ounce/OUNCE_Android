# 😸We-are-OUNCE 'Android' Developer

## WE ARE OUNCE!!

<img src="https://user-images.githubusercontent.com/55642709/87788123-c4677780-c877-11ea-8288-6196e9a88287.png">

## OUNCE의 멋있는 Logo

<img src ="https://user-images.githubusercontent.com/55642709/87788111-c0d3f080-c877-11ea-9b6b-7475fbd8f906.jpg" width="45%">

<img src = "https://user-images.githubusercontent.com/55642709/87788116-c3364a80-c877-11ea-844a-828460139728.jpg" width="45%" align="right">

## 목차

- [Code Convention](https://github.com/We-are-Ounce/OUNCE_Android/wiki/Code-Convention)
- [Library](#library)
- [기능 구현 방법](#기능-소개)
- [Project](#project)
- [팀원 소개](#팀원)

### Library

| 라이브러리                                                                                                                         | 목적                                                              |
| ---------------------------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------- |
| [Retrofit2](https://github.com/square/retrofit)                                                                                    | 서버 통신                                                         |
| [Gson](https://github.com/google/gson)                                                                                             | 서버에서 받아온 Json 객체를 Gson으로 변환                         |
| [Glide](https://github.com/bumptech/glide)                                                                                         | url을 이용한 이미지 뷰에 이미지 처리                              |
| [DotIndicator](https://github.com/tommybuonomo/dotsindicator?utm_source=android-arsenal.com&utm_medium=referral&utm_campaign=7127) | 뷰페이저 인디케이터                                               |
| [Cropper](https://github.com/ArthurHub/Android-Image-Cropper)                                                                      | 프로필 이미지 크기 조절을 위한 Crop 기능                          |
| [MaterialSearchBar](https://github.com/mancj/MaterialSearchBar)                                                                    | 검색창 구현                                                       |
| [TedPermission](https://github.com/ParkSangGwon/TedPermission)                                                                     | 갤러리 이용 시 권한 설정                                          |
| [TedKeyboardObserver](https://github.com/ParkSangGwon/TedKeyboardObserver)                                                         | 키보드 show/hide 리스너                                           |
| [RecyclerView\_+ SearchView](https://github.com/l2hyunwoo/OunceCustomSearchBar)                                                    | 최근 검색어 검색창                                                |
| [Sticky ScrollView](https://github.com/didikk/sticky-nestedscrollview)                                                             | 메인 뷰 헤더 Sticky 스크롤 구현                                   |
| [EasySharedPreference](https://github.com/AmanpreetYatin/Easy-SharedPreferences)                                                   | SharedPreference를 더 쉽게 사용하기 위해 사용                     |
| [ViewModelProviders, ViewModel](https://developer.android.com/jetpack/androidx/releases/lifecycle)                                 | 프래그먼트 간 데이터 전달에 필요한 ViewModel을 사용하기 위한 설정 |
| [CircleImageView](https://github.com/hdodenhof/CircleImageView)                                                                    | 사진을 원형으로 삽입하기 위해 사용                                |
| [RatingBarCustom](https://github.com/hedge-hog/RatingBar)                                                                          | 기록하기 뷰에서 레이팅 바 커스텀을 위해 사용                      |

### ConstraintLayout 사용처

- activity_login.xml : Guideline, chain (packed) 사용

- activity_sign_up.xml : 하단 버튼에 chain(packed) 사용

- activity_sign_up_finish.xml : 레이아웃 사용

- fragment_email_check.xml : Guideline 사용

- fragment_id.xml : Guideline 사용

- fragment_password.xml : Guideline 사용

- item_searchmain_similar.xml : chain(spread) 사용 (\*하단 이미지에 LinearLayout 사용)

- 그 외 비사용처를 제외한 모든 곳에서 사용

  - [activity_login.xml 소스코드 예시](https://github.com/We-are-Ounce/OUNCE_Android/blob/develop/app/src/main/res/layout/activity_login.xml#L195)

### ConstraintLayout 비사용처

- activity_home.xml : CoordinatorLayout, RelativeLayout 사용

  - 이유 : BottomAppBar 구현을 위한 레이아웃 채택

- activity_product_review.xml : CoordinatorLayout, RelativeLayout 사용

  - 이유 : CollapsingToolBarLayout을 사용하기 위해 채택

- activity_record.xml : CoordinatorLayout 사용

  - 이유: CollapsingToolBarLayout을 사용하기 위해 채택

- fragment_search.xml : LinearLayout 사용

  - 이유: 텍스트, 리소스 수평, 수직 간격 효율적인 설정을 위함

- item_product_review_review.xml : LinearLayout 사용

  - 이유: 텍스트, 리소스 수평, 수직 간격 효율적인 설정을 위함

- item_product_review_review.xml : LinearLayout 사용

  - 이유: 텍스트, 리소스 수평, 수직 간격 효율적인 설정을 위함

- main_review_list_item.xml : LinearLayout 사용

  - 이유: 텍스트, 리소스 수평, 수직 간격 효율적인 설정을 위함

- item_searchmain_similar.xml : 하단 이미지에 LinearLayout 사용

  - 이유: 리소스 수평 간격 효율적인 설정을 위함)

### 핵심 기능 소개 + 구현

- [키보드 액션과 EditText 커스텀](https://github.com/We-are-Ounce/OUNCE_Android/blob/master/document/keyboard.md)
- [Fragment에 ViewPager 삽입](https://github.com/We-are-Ounce/OUNCE_Android/blob/master/document/ViewPagerInFragment.md)
- [ViewPager에 화면전환효과 삽입](https://github.com/We-are-Ounce/OUNCE_Android/blob/master/document/PageTransformer.md)
- [StickyLayout을 이용한 헤더에 뷰 붙이기](https://github.com/We-are-Ounce/OUNCE_Android/blob/develop/document/StickyScrollView.md)
- [뷰 새로고침 하기](https://github.com/We-are-Ounce/OUNCE_Android/blob/develop/document/Refresh.md)
- [SearchView 포커스 시 하단 창 변경](https://github.com/We-are-Ounce/OUNCE_Android/blob/develop/document/ChangeViewIfFocus.md)
- [상단 스크롤 시 툴바 변경](https://github.com/We-are-Ounce/OUNCE_Android/blob/develop/document/ScrollToolbarChange.md)
- [검색창에서 검색 시 하단 창에 결과 보여주기](https://github.com/We-are-Ounce/OUNCE_Android/blob/develop/document/ViewModelDataTransfer.md)
- [프래그먼트에 뷰페이저 달기](https://github.com/We-are-Ounce/OUNCE_Android/blob/develop/document/ViewPagerInFragment.md)
- [웹 페이지 창을 앱에서 띄워주기](https://github.com/We-are-Ounce/OUNCE_Android/blob/develop/document/WebViewSameApp.md)
- [프로필 이미지 서버에 업로드](https://github.com/We-are-Ounce/OUNCE_Android/blob/develop/document/MultiPart.md)
- [기록하기 화면 구현]

### 프로젝트 구조

<img src = "https://user-images.githubusercontent.com/55642709/87784477-5324c600-c871-11ea-9b42-d51d179ae4bf.PNG"  width="50%">

## Kotlin

### Collection 함수 사용

- clear() : 리스트의 요소 전부 삭제

HomeFragment.kt, OtherActivity.kt -> 리뷰 필터 적용 후 리사이클러 아이템 초기화 시 사용

 <img src="https://user-images.githubusercontent.com/55642709/87785698-954f0700-c873-11ea-9fd6-1a830fe9d068.PNG"> 
 
 정렬기준이 변경 시 새로운 정렬 데이터를 받기 위해 사용

 <img src="https://user-images.githubusercontent.com/55642709/87785879-f24abd00-c873-11ea-8867-9c4a8c039809.PNG" width="74%">

- addall() : 리스트의 모든 요소를 전부 arrayList에 저장

**HomeFragment.kt, OtherActivity.kt**-> 리사이클러뷰 최하단 도착 시 추가적으로
데이터를 보여주기 위해 사용

 <img src = "https://user-images.githubusercontent.com/55642709/87786137-64bb9d00-c874-11ea-80b2-c0613bda308e.PNG">

- split(String) : 구분자를 기준으로 문자열들을 `List<String>`으로 파싱

- map(List) : 리스트 각각의 원소에 map안에 있는 함수를 실행하여 그 원소들로 다시 List를 생성

- trim(String): 문자열 앞 뒤에 있는 공백 제거

**ItemSearchActivity.kt**

 <img src = "https://user-images.githubusercontent.com/55642709/87788836-02b16680-c879-11ea-9d8b-89e9ded60d77.png">

### 확장 함수 사용 구간

- 로그 출력을 위한 확장 함수

  ```
  fun String.showLog(message: String?) {
    if (message != null) {
        Log.d(this, message)
    }
    else{
        Log.d(this,"null")
    }
  }
  ```

  다음과 같이 사용 (HomeFragment.kt showBottomSheet()함수에서 사용)

  <img src ="https://user-images.githubusercontent.com/55642709/87784886-17d6c700-c872-11ea-90c0-bd15a7fb372f.PNG">

### Project

- [Week 1](https://github.com/We-are-Ounce/OUNCE_Android/projects/1)
- [Week 2](https://github.com/We-are-Ounce/OUNCE_Android/projects/2)
- [Week 3](https://github.com/We-are-Ounce/OUNCE_Android/projects/3)

### 팀원

<table>
    <tr align="center">
        <td><B>팀원<B></td>
        <td width="200"><B>역할<B></td>
        <td><B>개인 목표<B></td>
    </tr>
    <tr align="center">
        <td>
            <img src="https://github.com/kangmin1012.png?size=100">
            <br>
            <a href="https://github.com/kangmin1012"><I>강민구</I></a>
        </td>
        <td width="100">안드로이드 개발</a></td>
        <td>역동적인 앱 + 플레이 스토어 출시!</td>
    </tr>
    <tr align="center">
        <td>
            <img src="https://github.com/l2hyunwoo.png?size=100">
            <br>
            <a href="https://github.com/l2hyunwoo"><I>이현우</I></a>
        </td>
        <td width="100">안드로이드 개발</a></td>
        <td>실제로 사용해도 소비자들이 만족하는 어플리케이션을 출시, 좋은 사람들과 앱잼 잘 치뤄나가서 평생친구 먹기</td>
    </tr>
    <tr align="center">
        <td>
            <img src="https://github.com/jooyae.png?size=100">
            <br>
            <a href="https://github.com/jooyae"><I>박주예</I></a>
        </td>
        <td width="100">안드로이드 개발</a></td>
        <td>Ounce 개발 맡은 역할 열심히 하기, 안드로이드 개발 과정 기록하기   </td>
    </tr>
</table>
