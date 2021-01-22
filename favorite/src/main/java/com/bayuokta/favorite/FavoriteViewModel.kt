package com.bayuokta.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bayuokta.core.domain.usecase.MovieUseCase

class FavoriteViewModel(movieUseCase: MovieUseCase) : ViewModel() {
    val favoriteMovie = movieUseCase.getFavoriteMovies().asLiveData()
}

