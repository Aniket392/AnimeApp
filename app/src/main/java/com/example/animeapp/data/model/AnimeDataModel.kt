package com.example.animeapp.data.model

import com.example.animeapp.data.AnimeApiResponse
import com.example.animeapp.data.AnimeDetailApiResponse
import com.example.animeapp.data.AnimeListData
import retrofit2.Call

interface AnimeDataModel {
    fun getAnimeList(): Call<AnimeApiResponse>

    fun getAnimeDetail(mallId: Int): Call<AnimeDetailApiResponse>
}