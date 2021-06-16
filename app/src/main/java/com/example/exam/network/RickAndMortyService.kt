package com.example.exam.network

import com.example.exam.model.Character
import com.example.exam.model.Episode
import com.example.exam.model.Location
import com.example.exam.model.PageResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface RickAndMortyService {
    @GET("character/{id}")
    suspend fun getCharacter(
        @Path("id") id: String
    ): Response<Character>

    @GET("character/{id}")
    suspend fun getCharacters(
        @Path("id") id: String = "",
        @Query("page") page: Int = 0,
        @QueryMap() options:Map<String,String> = mapOf()
    ): Response<PageResult<Character>>

    @GET("character/{id}")
    suspend fun getCharactersById(
        @Path("id") id: String = "",
        @Query("page") page: Int = 0,
        @QueryMap() options:Map<String,String> = mapOf()
    ): Response<List<Character>>

    @GET("episode/{id}")
    suspend fun getEpisode(
        @Path("id") id: String
    ): Response<Episode>

    @GET("episode/{id}")
    suspend fun getEpisodes(
        @Path("id") id: String = "",
        @Query("page") page: Int = 0,
        @QueryMap() options:Map<String,String> = mapOf()
    ): Response<PageResult<Episode>>

    @GET("location/{id}")
    suspend fun getLocation(
        @Path("id") id: String
    ): Response<Location>

    @GET("location/{id}")
    suspend fun getLocations(
        @Path("id") id: String = "",
        @Query("page") page: Int = 0,
        @QueryMap() options:Map<String,String> = mapOf()
    ): Response<PageResult<Location>>
}