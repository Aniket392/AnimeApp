package com.example.animeapp.ui.animedetail.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.animeapp.data.Genre
import com.example.animeapp.databinding.AnimeGenreItemBinding

class AnimeGenreAdapter : Adapter<AnimeGenreAdapter.AnimeGenreViewHolder>() {
    var animeGenreData : List<Genre> = emptyList()
    class AnimeGenreViewHolder(val binding: AnimeGenreItemBinding) : ViewHolder(binding.root) {
        fun bind(genre : Genre) {
            binding.genreName.text = genre.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeGenreViewHolder {
        return AnimeGenreViewHolder(AnimeGenreItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return animeGenreData.size
    }

    override fun onBindViewHolder(holder: AnimeGenreViewHolder, position: Int) {
        holder.bind(animeGenreData[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(animeGenreData : List<Genre>) {
        this.animeGenreData = animeGenreData
        notifyDataSetChanged()
    }
}