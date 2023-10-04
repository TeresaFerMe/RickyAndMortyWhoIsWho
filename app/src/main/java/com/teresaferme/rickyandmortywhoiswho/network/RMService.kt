package com.teresaferme.rickyandmortywhoiswho.network

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Query

interface RMService {
    @GET("character/")
    fun getCharacters(): Call<RMGetCharactersResponseModel>?

    @GET("character/")
    fun getMoreCharacters(@Query("page") page: Int): Call<RMGetCharactersResponseModel>?

    @GET("episode/")
    fun getEpisodes(): Call<RMGetEpisodesResponseModel>?
}

