package com.teresaferme.rickyandmortywhoiswho.feature.main

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.teresaferme.rickyandmortywhoiswho.RMRetrofitClientInstance
import com.teresaferme.rickyandmortywhoiswho.RMService
import com.teresaferme.rickyandmortywhoiswho.model.RMCharacter
import com.teresaferme.rickyandmortywhoiswho.model.RMGender
import com.teresaferme.rickyandmortywhoiswho.model.RMSpecies
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
        )?.getCharacters()?.enqueue(object : Callback<List<RMCharacter>?> {
            override fun onResponse(
                call: Call<List<RMCharacter>?>,
                response: Response<List<RMCharacter>?>
            ) {
                characterList.value = response.body()
            }

            override fun onFailure(call: Call<List<RMCharacter>?>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

}