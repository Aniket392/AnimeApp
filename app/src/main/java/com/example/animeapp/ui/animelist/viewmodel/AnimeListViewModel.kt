package com.example.animeapp.ui.animelist.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animeapp.data.AnimeApiResponse
import com.example.animeapp.data.model.AnimeDataModel
import com.example.animeapp.data.AnimeListData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AnimeListViewModel(val animeDataModel: AnimeDataModel) : ViewModel() {
    private val _animeListLiveData = MutableLiveData<List<AnimeListData>>()
    val animeListLiveData : LiveData<List<AnimeListData>> = _animeListLiveData

    private val _onLoading = MutableLiveData<Boolean>()
    val onLoading : LiveData<Boolean> = _onLoading

    fun getAnimeList() {
        viewModelScope.launch(Dispatchers.IO) {
            _onLoading.postValue(true)
            animeDataModel.getAnimeList().enqueue(object : Callback<AnimeApiResponse> {
                override fun onResponse(
                    call: Call<AnimeApiResponse>,
                    response: Response<AnimeApiResponse>
                ) {
                    if(response.isSuccessful) {
                        _onLoading.postValue(false);
                        _animeListLiveData.postValue(response.body()?.data?.map {
                            it
                        })
                    }
                }

                override fun onFailure(call: Call<AnimeApiResponse>, t: Throwable) {
                    Log.e("Aniket", t.message.toString())
                }

            })
        }
    }
}