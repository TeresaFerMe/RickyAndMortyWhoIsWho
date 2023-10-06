package com.teresaferme.rickyandmortywhoiswho.network

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RMCallback<T>(private val executeOnSuccess: (response: Response<T>) -> Unit) : Callback<T> {
    override fun onResponse(
        call: Call<T>, response: Response<T>
    ) {
        executeOnSuccess.invoke(response)
    }

    override fun onFailure(call: Call<T>, t: Throwable) {
        Log.e(this.javaClass.name, t.stackTraceToString())
    }
}