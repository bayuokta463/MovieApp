package com.bayuokta.core.domain.usecase

import com.bayuokta.core.domain.model.Movie
import com.bayuokta.core.domain.repository.IMovieRepository

class MovieInteractor(private val movieRepository: IMovieRepository): MovieUseCase {

    override fun getAllMovies() = movieRepository.getAllMovies()

    override fun getFavoriteMovies() = movieRepository.getFavoriteMovies()

    override fun setFavoriteMovies(movie: Movie, state: Boolean) = movieRepository.setFavoriteMovies(movie, state)
}