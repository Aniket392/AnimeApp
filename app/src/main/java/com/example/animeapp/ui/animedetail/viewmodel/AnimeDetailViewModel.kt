package com.example.animeapp.ui.animedetail.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animeapp.data.AnimeDetailApiResponse
import com.example.animeapp.data.AnimeDetailData
import com.example.animeapp.data.model.AnimeDataModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AnimeDetailViewModel(val animeDataModel: AnimeDataModel) : ViewModel(){
    private val _animeDetailLiveData = MutableLiveData<AnimeDetailData>()
    val animeDetailLiveData : LiveData<AnimeDetailData> = _animeDetailLiveData

    private val _onLoading = MutableLiveData<Boolean>()
    val onLoading : LiveData<Boolean> = _onLoading

    fun getAnimeDetailData(mal_id : Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _onLoading.postValue(true)
            animeDataModel.getAnimeDetail(mal_id).enqueue(object: Callback<AnimeDetailApiResponse>{
                override fun onResponse(
                    call: Call<AnimeDetailApiResponse>,
                    response: Response<AnimeDetailApiResponse>
                ) {
                    _onLoading.postValue(false)
                    _animeDetailLiveData.postValue(response.body()?.data)
                }

                override fun onFailure(call: Call<AnimeDetailApiResponse>, t: Throwable) {
                    Log.e("Aniket", t.message.toString())
                }
            })
        }
    }
}