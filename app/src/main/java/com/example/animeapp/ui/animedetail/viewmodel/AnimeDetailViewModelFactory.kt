package com.example.animeapp.ui.animedetail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.animeapp.data.model.AnimeDataModel
import com.example.animeapp.ui.animelist.viewmodel.AnimeListViewModel

class AnimeDetailViewModelFactory(val animeDataModel: AnimeDataModel) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(AnimeDetailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AnimeDetailViewModel(animeDataModel) as T
        }
        return super.create(modelClass)
    }
}