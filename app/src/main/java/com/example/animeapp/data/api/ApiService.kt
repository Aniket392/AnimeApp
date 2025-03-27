package com.example.animeapp.data.api

import com.example.animeapp.data.AnimeApiResponse
import com.example.animeapp.data.AnimeDetailApiResponse
import com.example.animeapp.data.AnimeDetailData
import com.example.animeapp.data.AnimeListData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("top/anime")
    fun getAnimeList(): Call<AnimeApiResponse>

    @GET("anime/{anime_id}")
    fun getAnimeDetail(@Path("anime_id") malId: Int): Call<AnimeDetailApiResponse>
}