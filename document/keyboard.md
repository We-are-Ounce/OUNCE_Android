# EditText 다양한 액션들

> ## TedKeyboardObserve를 사용해 키보드가 내려갈 경우 EditText의 포커스를 없애준다.
>
> <img src ="https://user-images.githubusercontent.com/55642709/86792796-c9713d80-c0a5-11ea-832a-6e8709265505.gif" height="40%" width="40%">

### Gradle 세팅

```
dependency{
    implementation 'gun0912.ted:tedpermission:2.2.3'
}
```

### 레이아웃 바깥 클릭 시 키보드 내리기

```
        val mImm : InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
 //EditText 화면 바깥 선택 시 키보드 숨기기
        layout_login_container.setOnClickListener {
            mImm.hideSoftInputFromWindow(edt_login_id.windowToken, 0)
        }
```

### 키보드가 내려가는 것을 감지 후 포커스 해제

```
override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        ...

TedKeyboardObserver(this)
                .listen { isShow ->
                    if (!isShow) {
                        // do checking EditText
                        edt_login_id.clearFocus()
                        edt_login_password.clearFocus()
                    }
                }

        ...
}
```

> ## EditText에 글씨를 쓸 경우 우측에 x 표시 활성화 + 누를 시 내용 삭제
>
> <img src="https://user-images.githubusercontent.com/55642709/86794829-e60e7500-c0a7-11ea-995b-e13bbbe379b5.gif" width="40%" height="40%">

### CustomEditText 클래스 생성

```
class ClearEditText :
    AppCompatEditText,
    TextWatcher,
    View.OnTouchListener,
    View.OnFocusChangeListener {

    private var clearDrawable: Drawable? = null
    private var onFocus: OnFocusChangeListener? = null
    private var onTouch: OnTouchListener? = null

    constructor(context: Context?) : super(context!!)

    constructor(context: Context?, attrs: AttributeSet) : super(context!!, attrs)


    constructor(context: Context?, attrs: AttributeSet, defStyleAttr: Int)
            : super(context!!, attrs, defStyleAttr)

    init {
        val temDrawable = ContextCompat.getDrawable(context, R.drawable.ic_expand_close)
        clearDrawable = temDrawable?.let { DrawableCompat.wrap(it) }
        clearDrawable?.let { DrawableCompat.setTintList(it, hintTextColors) }
        clearDrawable?.setBounds(0,
            0,
            clearDrawable!!.intrinsicWidth,
            clearDrawable!!.intrinsicHeight
        )

        setClearIconVisible(false)

        super.setOnTouchListener(this)
        super.setOnFocusChangeListener(this)
        addTextChangedListener(this)


    }

    override fun onFocusChange(view: View?, hasFocus: Boolean) {
        if(hasFocus){
            setClearIconVisible(text?.length!! > 0)
        }else{
            setClearIconVisible(false)
        }

        if(onFocus != null){
            onFocus?.onFocusChange(view, hasFocus)
        }
    }

    override fun onTouch(view: View?, motionEvent: MotionEvent?): Boolean {
        val x = motionEvent?.x?.toInt()

        if(clearDrawable!!.isVisible && x!! > width - paddingRight - clearDrawable!!.intrinsicWidth){
            if(motionEvent.action == MotionEvent.ACTION_UP){
                text = null
            }

            return true
        }

        if (onTouch != null){
            return onTouch!!.onTouch(view,motionEvent)
        }else{
            return false
        }
    }

    override fun onTextChanged(
        text: CharSequence?,
        start: Int,
        lengthBefore: Int,
        lengthAfter: Int
    ) {
        if(isFocused){
            setClearIconVisible(text!!.isNotEmpty())
        }
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

    override fun afterTextChanged(p0: Editable?) {}

    private fun setClearIconVisible(visible : Boolean){
        clearDrawable?.setVisible(visible,false)
        setCompoundDrawables(null, null, if (visible) clearDrawable else null, null)
    }
}
```

### EditText 대신 해당 클래스 사용

```
 <com.sopt.ounce.util.ClearEditText
    android:width="wrap_content"
    android:height="wrap_content
    ...
 />
```
