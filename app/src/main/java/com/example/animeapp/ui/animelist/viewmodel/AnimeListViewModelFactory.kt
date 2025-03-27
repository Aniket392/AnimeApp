package com.example.animeapp.ui.animelist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.animeapp.data.model.AnimeDataModel

class AnimeListViewModelFactory(val animeDataModel: AnimeDataModel) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        if(modelClass.isAssignableFrom(AnimeListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AnimeListViewModel(animeDataModel) as T
        }
        return super.create(modelClass, extras)
    }
}