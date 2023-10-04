package com.teresaferme.rickyandmortywhoiswho.network

import com.google.gson.annotations.SerializedName
import com.teresaferme.rickyandmortywhoiswho.model.RMCharacter

class RMGetCharactersResponseModel(
    @SerializedName("info")
    val info: RMGetInfoResponseModel,
    @SerializedName("results")
    val results: List<RMCharacter>
)