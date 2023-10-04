package com.teresaferme.rickyandmortywhoiswho.network

import com.google.gson.annotations.SerializedName
import com.teresaferme.rickyandmortywhoiswho.model.RMCharacter

class RMGetEpisodesResponseModel(
    @SerializedName("info")
    val info: RMGetInfoResponseModel
)