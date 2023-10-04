package com.teresaferme.rickyandmortywhoiswho.feature.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teresaferme.rickyandmortywhoiswho.network.RMGetCharactersResponseModel
import com.teresaferme.rickyandmortywhoiswho.network.RMRetrofitClientInstance
import com.teresaferme.rickyandmortywhoiswho.network.RMService
import com.teresaferme.rickyandmortywhoiswho.model.RMCharacter
import com.teresaferme.rickyandmortywhoiswho.network.RMGetEpisodesResponseModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainViewModel: ViewModel() {
    var characterList: MutableLiveData<List<RMCharacter>?> = MutableLiveData()
    var episodeNumber: MutableLiveData<Int?> = MutableLiveData()

    init {
        viewModelScope.launch {
            getCharacterList()
        }
        viewModelScope.launch {
            getEpisodeList()
        }
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

    private fun getEpisodeList() {
        RMRetrofitClientInstance().getRetrofitInstance()?.create(
            RMService::class.java
        )?.getEpisodes()?.enqueue(object : Callback<RMGetEpisodesResponseModel> {
            override fun onResponse(
                call: Call<RMGetEpisodesResponseModel>,
                response: Response<RMGetEpisodesResponseModel>
            ) {
                episodeNumber.value = response.body()?.info?.count
            }

            override fun onFailure(call: Call<RMGetEpisodesResponseModel>, t: Throwable) {
                Log.e(this.javaClass.name, t.stackTraceToString())
            }

        })
    }

}