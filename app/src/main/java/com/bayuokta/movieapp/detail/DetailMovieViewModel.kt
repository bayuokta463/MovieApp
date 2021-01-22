package com.bayuokta.movieapp.detail

import androidx.lifecycle.ViewModel
import com.bayuokta.core.domain.model.Movie
import com.bayuokta.core.domain.usecase.MovieUseCase

class DetailMovieViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {
    fun setFavoriteMovie(movie: Movie, newStatus:Boolean) =
        movieUseCase.setFavoriteMovies(movie, newStatus)
}

