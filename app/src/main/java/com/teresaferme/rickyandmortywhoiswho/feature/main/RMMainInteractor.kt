package com.teresaferme.rickyandmortywhoiswho.feature.main

import android.util.Log
import com.teresaferme.rickyandmortywhoiswho.model.RMCharacter
import com.teresaferme.rickyandmortywhoiswho.network.RMGetCharactersResponseModel
import com.teresaferme.rickyandmortywhoiswho.network.RMGetEpisodesResponseModel
import com.teresaferme.rickyandmortywhoiswho.network.RMRetrofitClientInstance
import com.teresaferme.rickyandmortywhoiswho.network.RMService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RMMainInteractor {
    var characters: MutableList<RMCharacter>? = mutableListOf()
    private var nextPage: String? = null

    fun getMoreCharacters(callback: (MutableList<RMCharacter>?) -> Unit) {
        nextPage?.let { nextPageUrl ->
            RMRetrofitClientInstance().getRetrofitInstance()?.create(
                RMService::class.java
            )?.getMoreCharacters(nextPageUrl)
                ?.enqueue(object : Callback<RMGetCharactersResponseModel> {
                    override fun onResponse(
                        call: Call<RMGetCharactersResponseModel>,
                        response: Response<RMGetCharactersResponseModel>
                    ) {
                        val list = characters?.toMutableList()
                        response.body()?.let { body ->
                            list?.addAll(body.results as Collection<RMCharacter>)
                            characters = list
                            callback.invoke(characters)
                            nextPage = body.info.next
                        }
                    }

                    override fun onFailure(call: Call<RMGetCharactersResponseModel>, t: Throwable) {
                        Log.e(this.javaClass.name, t.stackTraceToString())
                    }
                })
        }
    }

    fun getCharacterList(callback: (MutableList<RMCharacter>?) -> Unit) {
        RMRetrofitClientInstance().getRetrofitInstance()?.create(
            RMService::class.java
        )?.getCharacters()?.enqueue(object : Callback<RMGetCharactersResponseModel> {
            override fun onResponse(
                call: Call<RMGetCharactersResponseModel>,
                response: Response<RMGetCharactersResponseModel>
            ) {
                response.body()?.let { body ->
                    characters = body.results as MutableList<RMCharacter>
                    nextPage = body.info.next
                    callback.invoke(characters)
                }
            }

            override fun onFailure(call: Call<RMGetCharactersResponseModel>, t: Throwable) {
                Log.e(this.javaClass.name, t.stackTraceToString())
            }

        })
    }

    fun getEpisodeList(callback: (Int?) -> Unit) {
        RMRetrofitClientInstance().getRetrofitInstance()?.create(
            RMService::class.java
        )?.getEpisodes()?.enqueue(object : Callback<RMGetEpisodesResponseModel> {
            override fun onResponse(
                call: Call<RMGetEpisodesResponseModel>,
                response: Response<RMGetEpisodesResponseModel>
            ) {
                callback.invoke(response.body()?.info?.count)
            }

            override fun onFailure(call: Call<RMGetEpisodesResponseModel>, t: Throwable) {
                Log.e(this.javaClass.name, t.stackTraceToString())
            }
        })
    }
}