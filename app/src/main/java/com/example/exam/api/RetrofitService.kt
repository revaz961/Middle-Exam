package com.example.exam.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {
    private const val BASE_URL = "https://rickandmortyapi.com/api/"

    val retrofitService: RickAndMortyService by lazy {
        Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(RickAndMortyService::class.java)
    }
}