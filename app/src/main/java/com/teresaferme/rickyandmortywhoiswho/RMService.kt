package com.teresaferme.rickyandmortywhoiswho

import com.google.gson.annotations.SerializedName
import com.teresaferme.rickyandmortywhoiswho.model.RMCharacter
import retrofit2.Call
import retrofit2.http.GET

interface RMService {
    @GET("character/")
    fun getCharacters(): Call<RMGetCharactersResponseModel>?
}

class RMGetCharactersResponseModel(
    @SerializedName("info")
    val info: RMGetCharactersInfoResponseModel,
    @SerializedName("results")
    val results: List<RMCharacter>
)

class RMGetCharactersInfoResponseModel(
    @SerializedName("count")
    val count: Int,
    @SerializedName("pages")
    val pages: Int,
    @SerializedName("next")
    val next: String?,
    @SerializedName("prev")
    val prev: String?
)