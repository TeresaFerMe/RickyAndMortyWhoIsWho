package com.teresaferme.rickyandmortywhoiswho

import com.teresaferme.rickyandmortywhoiswho.model.RMCharacter
import retrofit2.Call
import retrofit2.http.GET




interface RMService {
    @GET("character/")
    fun getCharacters(): Call<List<RMCharacter>>?
}