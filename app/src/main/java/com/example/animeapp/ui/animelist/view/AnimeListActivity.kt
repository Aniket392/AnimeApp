package com.example.animeapp.ui.animelist.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.animeapp.data.model.AnimeDataModelImpl
import com.example.animeapp.databinding.AnimeListBinding
import com.example.animeapp.ui.animelist.viewmodel.AnimeListViewModel
import com.example.animeapp.ui.animelist.viewmodel.AnimeListViewModelFactory
import com.example.animeapp.ui.animedetail.view.AnimeDetailActivity

class AnimeListActivity : AppCompatActivity(), ClickListener {
    private lateinit var binding : AnimeListBinding
    private lateinit var viewModel : AnimeListViewModel
    private var animeDataModel = AnimeDataModelImpl()
    private lateinit var animeAdapter : AnimeListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AnimeListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        animeAdapter = AnimeListAdapter()
        animeAdapter.setClickListener(this)

        binding.recyclerView.adapter = animeAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel = ViewModelProvider(this, AnimeListViewModelFactory(animeDataModel))[AnimeListViewModel::class.java]
        viewModel.getAnimeList()

        viewModel.animeListLiveData.observe(this) {
            animeAdapter.setListData(it)
        }

        viewModel.onLoading.observe(this) { isLoading->
            binding.loadingAnimation.visibility = if (isLoading) View.VISIBLE else View.GONE
            binding.recyclerView.visibility = if (isLoading) View.GONE else View.VISIBLE
        }

        viewModel.onFailed.observe(this) {
            Toast.makeText(this, "Internet not Working!!!", Toast.LENGTH_LONG).show()
        }
    }

    override fun onClick(id: Int) {
        val intent = Intent(this, AnimeDetailActivity::class.java)
        intent.putExtra("mal_Id", id)
        startActivity(intent)
    }


}