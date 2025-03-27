package com.example.animeapp.data

data class AnimeDetailData(
    val mal_id: Int,
    val images: ImageWrapper,
    val trailer: Trailer?,
    val title: String,
    val episodes: Int?,
    val rating: String?,
    val synopsis: String?,
    val genres: List<Genre>
)
