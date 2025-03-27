package com.example.animeapp.data.model

import com.example.animeapp.data.AnimeApiResponse
import com.example.animeapp.data.AnimeDetailApiResponse
import com.example.animeapp.data.AnimeListData
import com.example.animeapp.data.api.RetrofitClient
import retrofit2.Call

class AnimeDataModelImpl: AnimeDataModel {
    override fun getAnimeList(): Call<AnimeApiResponse> {
        return RetrofitClient.apisrevice.getAnimeList()
    }

    override fun getAnimeDetail(mallId: Int): Call<AnimeDetailApiResponse> {
        return RetrofitClient.apisrevice.getAnimeDetail(mallId)
    }
}