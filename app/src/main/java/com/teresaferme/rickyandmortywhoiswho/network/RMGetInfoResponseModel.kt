package com.teresaferme.rickyandmortywhoiswho.network

import com.google.gson.annotations.SerializedName

class RMGetInfoResponseModel(
    @SerializedName("count")
    val count: Int,
    @SerializedName("pages")
    val pages: Int,
    @SerializedName("next")
    val next: String?,
    @SerializedName("prev")
    val prev: String?
)