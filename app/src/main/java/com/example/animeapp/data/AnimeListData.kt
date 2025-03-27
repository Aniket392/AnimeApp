package com.example.animeapp.data

data class AnimeListData(
    val mal_id: Int,
    val title: String,
    val episodes: Int?,
    val score: Float,
    val images: ImageWrapper
)

