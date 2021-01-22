package com.bayuokta.movieapp.detail

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bayuokta.movieapp.R
import com.bayuokta.core.domain.model.Movie
import com.bayuokta.movieapp.databinding.ActivityDetailMovieBinding
import com.bumptech.glide.Glide
import org.koin.android.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

class DetailMovieActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private val detailMovieViewModel: DetailMovieViewModel by viewModel()
    private lateinit var binding: ActivityDetailMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val detailMovie = intent.getParcelableExtra<Movie>(EXTRA_DATA)
        showDetailMovie(detailMovie)

        binding.ibBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun showDetailMovie(detailMovie: Movie?) {
        detailMovie?.let {

            val voteAverage = (detailMovie.voteAverage/2).toFloat()

            Glide.with(this@DetailMovieActivity)
                .load(getString(R.string.url_image)+detailMovie.posterPath)
                .into(binding.ivPoster)

            Glide.with(this@DetailMovieActivity)
                .load(getString(R.string.url_image)+detailMovie.backdropPath)
                .into(binding.ivBackdrop)

            binding.tvTitle.text = detailMovie.title
            binding.rbRatingAverage.rating = voteAverage
            binding.includeContentMain.tvReleaseDate.text = parsingDate(detailMovie.releaseDate)
            binding.includeContentMain.tvOverview.text = detailMovie.overview

            var statusFavorite = detailMovie.isFavorite
            setStatusFavorite(statusFavorite)
            binding.fab.setOnClickListener {
                statusFavorite = !statusFavorite
                detailMovieViewModel.setFavoriteMovie(detailMovie, statusFavorite)
                setStatusFavorite(statusFavorite)
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite))
        } else {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_not_favorite))
        }
    }

    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    private fun parsingDate(dateMovie:String) : String {
        val inputDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = inputDate.parse(dateMovie)
        val outputDate = SimpleDateFormat("EEE, d MMM yyyy", Locale.getDefault())

        return outputDate.format(date)
    }
}
