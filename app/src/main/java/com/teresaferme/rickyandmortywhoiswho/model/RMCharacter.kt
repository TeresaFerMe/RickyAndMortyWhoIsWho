package com.teresaferme.rickyandmortywhoiswho.model

import com.google.gson.annotations.SerializedName

class RMCharacter(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("species")
    val species: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("origin")
    val origin: String,
    @SerializedName("location")
    val location: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("episode")
    val episode: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("created")
    val created: String
) {
    fun getGender() = RMGender.getFrom(value = gender)
    fun getSpecies() = RMSpecies.getFrom(value = species)
}
