package com.teresaferme.rickyandmortywhoiswho.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RMRetrofitClientInstance {
    private var retrofit: Retrofit? = null
    private val BASE_URL = "https://rickandmortyapi.com/api/"

    fun getRetrofitInstance(): Retrofit? {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit
    }
}