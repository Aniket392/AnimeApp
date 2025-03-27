package com.example.animeapp.ui.animelist.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.animeapp.R
import com.example.animeapp.data.AnimeListData
import com.example.animeapp.databinding.AnimeItemBinding

class AnimeListAdapter : Adapter<AnimeListAdapter.AnimeViewHolder>() {
    private var animeData: List<AnimeListData> = emptyList()
    private lateinit var listener : ClickListener
    class AnimeViewHolder(val binding: AnimeItemBinding) : ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(animeListData: AnimeListData, listener: ClickListener) {
            binding.rating.text = "${binding.root.context.getString(R.string.rating)} : ${animeListData.score}"
            binding.title.text = animeListData.title
            binding.numberOfEpisode.text = "${binding.root.context.getString(R.string.no_of_episodes)} : ${animeListData.episodes}"
            Glide.with(binding.root.context)
                .load(animeListData.images.jpg.image_url)
                .placeholder(R.drawable.placeholder_image)
                .into(binding.posterImage)

            binding.root.setOnClickListener{
                listener.onClick(animeListData.mal_id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        return AnimeViewHolder(AnimeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return animeData.size
    }

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        holder.bind(animeData[position], listener)
    }

    fun setClickListener(listener: ClickListener) {
        this.listener = listener
    }

    fun setListData(animeData: List<AnimeListData>) {
        this.animeData = animeData
        notifyDataSetChanged()
    }
}