package com.example.animeapp.ui.animedetail.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.animeapp.R
import com.example.animeapp.data.model.AnimeDataModelImpl
import com.example.animeapp.databinding.AnimeDetailBinding
import com.example.animeapp.ui.animedetail.viewmodel.AnimeDetailViewModel
import com.example.animeapp.ui.animedetail.viewmodel.AnimeDetailViewModelFactory

class AnimeDetailActivity : AppCompatActivity() {
    private lateinit var binding: AnimeDetailBinding
    private lateinit var viewModel: AnimeDetailViewModel
    private var animeDataModel = AnimeDataModelImpl()
    private lateinit var animeGenreAdapter: AnimeGenreAdapter

    @SuppressLint("SetTextI18n", "SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AnimeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(
            this,
            AnimeDetailViewModelFactory(animeDataModel)
        )[AnimeDetailViewModel::class.java]
        intent?.let {
            var id = intent.getIntExtra("mal_Id", 0)
            viewModel.getAnimeDetailData(id)
        }

        animeGenreAdapter = AnimeGenreAdapter()
        binding.genreRecyclerView.adapter = animeGenreAdapter
        binding.genreRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        viewModel.animeDetailLiveData.observe(this) {
            binding.titleTextView.text = it.title
            binding.episodeTextView.text =
                "${binding.root.context.getString(R.string.rating)} : ${it.rating}"
            binding.ratingTextView.text =
                "${binding.root.context.getString(R.string.no_of_episodes)} : ${it.episodes}"
            binding.synopsisTextView.text = it.synopsis
            animeGenreAdapter.setData(it.genres)

            if (it.trailer != null) {
                Log.d("Aniket", it.trailer.embed_url)
                initializeWebView(it.trailer.embed_url)
            } else {
                binding.playerView.visibility = View.GONE
                binding.posterImageView.visibility = View.VISIBLE
                Glide.with(this)
                    .load(it.images.jpg.image_url)
                    .placeholder(R.drawable.placeholder_image)
                    .into(binding.posterImageView)
            }
        }

        viewModel.onLoading.observe(this) { isLoading->
            if(isLoading) {
                binding.loadingAnimation.visibility = View.VISIBLE
                binding.contentContainer.visibility = View.GONE
                binding.titleTextView.visibility = View.GONE
                binding.cardView.visibility = View.GONE
            }
            else {
                binding.loadingAnimation.visibility = View.GONE
                binding.contentContainer.visibility = View.VISIBLE
                binding.titleTextView.visibility = View.VISIBLE
                binding.cardView.visibility = View.VISIBLE
            }
        }
    }

    override fun onPause() {
        super.onPause()
        binding.playerView.onPause()
        binding.playerView.pauseTimers()
    }

    override fun onResume() {
        super.onResume()
        binding.playerView.onResume()
        binding.playerView.resumeTimers()
    }

    private fun initializeWebView(url: String) {
        val frameVideo = """
            <html>
            <head>
                <style>
                    body { margin: 0; padding: 0; }
                    iframe {
                        width: 100vw;
                        height: 320px;
                        border: none;
                    }
                </style>
            </head>
            <body>
                <iframe src="$url" title="YouTube Video" allowfullscreen></iframe>
            </body>
            </html>
        """.trimIndent()

        binding.playerView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                return false
            }
        }
        //web settings for JavaScript Mode
        val webSettings = binding.playerView.settings
        webSettings.javaScriptEnabled = true
        webSettings.domStorageEnabled = true
        binding.playerView.loadData(frameVideo, "text/html", "utf-8")
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.playerView.destroy()
    }
}