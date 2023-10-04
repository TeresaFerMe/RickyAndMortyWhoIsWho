package com.teresaferme.rickyandmortywhoiswho.feature.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.teresaferme.rickyandmortywhoiswho.RMGetCharactersResponseModel
import com.teresaferme.rickyandmortywhoiswho.RMRetrofitClientInstance
import com.teresaferme.rickyandmortywhoiswho.RMService
import com.teresaferme.rickyandmortywhoiswho.model.RMCharacter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainViewModel: ViewModel() {
    var characterList: MutableLiveData<List<RMCharacter>?> = MutableLiveData()

    init {
        this.getCharacterList()
    }

    private fun getCharacterList() {
        RMRetrofitClientInstance().getRetrofitInstance()?.create(
            RMService::class.java
        )?.getCharacters()?.enqueue(object : Callback<RMGetCharactersResponseModel> {
            override fun onResponse(
                call: Call<RMGetCharactersResponseModel>,
                response: Response<RMGetCharactersResponseModel>
            ) {
                characterList.value = response.body()?.results
            }

            override fun onFailure(call: Call<RMGetCharactersResponseModel>, t: Throwable) {
                Log.e(this.javaClass.name, t.stackTraceToString())
            }

        })
    }

}