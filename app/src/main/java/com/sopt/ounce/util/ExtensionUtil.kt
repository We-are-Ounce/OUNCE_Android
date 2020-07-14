package com.sopt.ounce.util


import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun EditText.textCheckListener(textCheck: (CharSequence?) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
        }

        override fun beforeTextChanged(
            s: CharSequence?,
            start: Int,
            count: Int,
            after: Int
        ) = Unit

        override fun onTextChanged(
            s: CharSequence?,
            start: Int,
            before: Int,
            count: Int
        ) {
            textCheck(s)
        }
    })
}

fun <ResponseType> Call<ResponseType>.customEnqueue(
    onFaile: () -> Unit = { "netWork".showLog("통신 실패") },
    onSuccess: (ResponseType) -> Unit,
    onError: (Response<ResponseType>) -> Unit = {}
) {
    this.enqueue(object : Callback<ResponseType> {
        override fun onFailure(call: Call<ResponseType>, t: Throwable) {
            onFaile()
        }

        override fun onResponse(call: Call<ResponseType>, response: Response<ResponseType>) {
            response.takeIf { it.isSuccessful }
                ?.body()
                ?.let{
                    onSuccess(it)
                } ?: onError(response)
        }
    })
}

fun String.showLog(message: String?) {
    if (message != null) {
        Log.d(this, message)
    }
    else{
        Log.d(this,"null")
    }
}
