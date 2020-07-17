# 화면 위로 스크롤 시 새로고침 실행

## 구현 화면

<img src="https://user-images.githubusercontent.com/55642709/87779977-b52cfd80-c868-11ea-844a-78e8c74d3b06.gif" width="40%">

## 구현 방법

### SwipeLayout으로 xml을 감싸준다.

```
<androidx.swiperefreshlayout.widget.SwipeRefreshLayout
        android:id="@+id/swipe_main_refresh"
        android:layout_width="match_parent"
        android:layout_height="match_parent">

        <androidx.constraintlayout.widget.ConstraintLayout
            android:id="@+id/layout_main_content"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:background="@color/white_five">
        </androidx.constraintlayout.widget.ConstraintLayout>

</androidx.swiperefreshlayout.widget.SwipeRefreshLayout>
```

### 코드에서 `setOnRefreshListener`를 구현해준다.

```
        swipe_main_refresh.setOnRefreshListener {
            val intent = Intent(this, MainActivity::class.java)
            finish()
            overridePendingTransition(0,0)
            startActivity(intent)


            swipe_main_refresh.isRefreshing = false
        }
```

### 새로고침 화살표의 색을 변경할 때 작성해준다.

```
 swipe_main_refresh.setColorSchemeColors(resources.getColor(R.color.dark_peach))
```
