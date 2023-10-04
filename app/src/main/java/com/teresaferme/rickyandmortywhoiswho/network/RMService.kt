package com.teresaferme.rickyandmortywhoiswho.network

import com.teresaferme.rickyandmortywhoiswho.model.RMCharacter
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface RMService {
    @GET("character/")
    fun getCharacters(): Call<RMGetCharactersResponseModel>?

    @GET
    fun getMoreCharacters(@Url url: String): Call<RMGetCharactersResponseModel>?

    @GET("episode/")
    fun getEpisodes(): Call<RMGetEpisodesResponseModel>?

    @GET
    fun getCharacterDetail(@Url url: String): Call<RMCharacter>
}

