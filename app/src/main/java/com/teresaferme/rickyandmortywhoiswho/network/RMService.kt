package com.teresaferme.rickyandmortywhoiswho.network

import com.google.gson.annotations.SerializedName
import com.teresaferme.rickyandmortywhoiswho.model.RMCharacter
import retrofit2.Call
import retrofit2.http.GET

interface RMService {
    @GET("character/")
    fun getCharacters(): Call<RMGetCharactersResponseModel>?

    @GET("episode/")
    fun getEpisodes(): Call<RMGetEpisodesResponseModel>?
}

