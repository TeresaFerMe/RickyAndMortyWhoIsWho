package com.teresaferme.rickyandmortywhoiswho.feature.main

import com.teresaferme.rickyandmortywhoiswho.model.RMCharacter
import com.teresaferme.rickyandmortywhoiswho.network.RMCallback
import com.teresaferme.rickyandmortywhoiswho.network.RMGetCharactersResponseModel
import com.teresaferme.rickyandmortywhoiswho.network.RMGetEpisodesResponseModel
import com.teresaferme.rickyandmortywhoiswho.network.RMRetrofitClientInstance
import com.teresaferme.rickyandmortywhoiswho.network.RMService

class RMMainInteractor {
    var characters: MutableList<RMCharacter>? = mutableListOf()
    private var nextPage: String? = null
    private var service = RMRetrofitClientInstance().getRetrofitInstance()?.create(
        RMService::class.java
    )

    fun getMoreCharacters(callback: (MutableList<RMCharacter>?) -> Unit) {
        nextPage?.let { nextPageUrl ->
            service?.getMoreCharacters(nextPageUrl)
                ?.enqueue(RMCallback<RMGetCharactersResponseModel> { response ->
                    val list = characters?.toMutableList()
                    response.body()?.let { body ->
                        list?.addAll(body.results as Collection<RMCharacter>)
                        characters = list
                        callback.invoke(characters)
                        nextPage = body.info.next
                    }
                })
        }
    }

    fun getCharacterList(callback: (MutableList<RMCharacter>?) -> Unit) {
        service?.getCharacters()?.enqueue(RMCallback<RMGetCharactersResponseModel> { response ->
            response.body()?.let { body ->
                characters = body.results as MutableList<RMCharacter>
                nextPage = body.info.next
                callback.invoke(characters)
            }
        })
    }

    fun getEpisodeList(callback: (Int?) -> Unit) {
        service?.getEpisodes()?.enqueue(RMCallback<RMGetEpisodesResponseModel> { response ->
            callback.invoke(response.body()?.info?.count)
        })
    }
}