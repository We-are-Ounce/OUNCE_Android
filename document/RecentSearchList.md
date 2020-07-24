# 최근 검색어 리스트 구현
## 계속 리스트를 가지고 있으려면 SharedPreference(EasySharedPreference)에 저장해야한다, 그러나..
EasySharedPreference에 putListString을 넣으면 검색어(String) 리스트가 저장된다.
하지만 ArrayList에 무작정 검색 결과를 add를 하면 구분자를 생성하여 String이 연결된다

## 스트링을 구분자를 기준으로 Parsing하고 Adapter에 연결
```
if(!listItem.isNullOrEmpty()){
  val listAdapter = listItem[0].split(",=,").map{it.trim()}.toMutableList()
  if(listAdapter.size > 3)
    adapter = RecyclerViewAdapter(listAdapter.subList(0, 3).toList() as ArrayList<String>)
  else
    adapter = RecyclerViewAdapter(listAdapter as ArrayList<String>)
  rv_record_search.adapter = adapter
}
```
+ 최근 검색어는 리스트에 마지막에 넣지 않고(add 함수 사용하지 않음), add(index, query)를 활용하여 리스트 맨 처음에 부착한다.
+ 리스트를 EasySharedPreference를 활용하여 앱 내에 계속 
+ 리스트의 구분자를 없애주고(split 함수) 리스트로 저장한다.
+ 최근 검색어는 3개만 보여주고 싶기에 사이즈가 3보다 클 경우 부분 리스트를 가져와서 어댑터에 부착시킨다.
