# 헤더에 달라 붙는 스크롤 뷰 만들기

## 구현 화면

<img src = "https://user-images.githubusercontent.com/55642709/87779855-77c87000-c868-11ea-977d-8a0b92ca31c4.gif" width="40%"/>

## 구현 방법

### 레이아웃을 StickyNestedLayout 태그로 감싸준다.

```
<androidx.constraintlayout.widget.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/layout_review"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="@color/white"
    tools:context=".main.ui.HomeFragment">

    <me.didik.component.StickyNestedScrollView
        android:id="@+id/sticky_scroll_main"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:fillViewport="true"
        android:overScrollMode="never"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent">

        <androidx.constraintlayout.widget.ConstraintLayout
            android:id="@+id/layout_review_container"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:background="@color/white">

            ...
        </androidx.constraintlayout.widget.ConstraintLayout>
    </me.didik.component.StickyNestedScrollView
</androidx.constraintlayout.widget.ConstraintLayout
```

### 화면 상단에 붙을 헤더에 `android:tag = "sticky"`를 붙여준다.

```
<androidx.constraintlayout.widget.ConstraintLayout
                android:id="@+id/layout_main_review_list"
                ...
                android:tag="sticky"
                ...
>
```
