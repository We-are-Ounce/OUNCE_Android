# WebView를 동일 앱에서 띄워볼까요
## 설정을 조금만 변경한다면 앱에서 띄울 수 있습니다.
### WebView 기본 구현 방식
WebViewActivity 중 일부
```
val intent = intent
//접속하고 싶은 url 가져오기
val webUrl = "{접속하고 싶은 웹페이지 경로}"
webView.loadUrl(webUrl)
```
위와 같이 Intent를 제작하여 loadUrl 함수를 사용하여 앱을 통해 홈페이지로 접속할 수 있으나
** 웹뷰가 실행되면서 새로운 창이 켜진다 **

### webViewClient 속성을 설정하여 앱 내에서 웹페이지를 띄우도록 합시다
```
webView.webViewClient = WebViewClient()
```
위의 코드를 작성하면 앱 내에서 웹페이지를 브라우즈 할 수 있다.
