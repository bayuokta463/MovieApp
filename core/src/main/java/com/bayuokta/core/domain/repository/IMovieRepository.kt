package com.bayuokta.core.domain.repository

import com.bayuokta.core.data.Resource
import com.bayuokta.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {

    fun getAllMovies(): Flow<Resource<List<Movie>>>

    fun getFavoriteMovies(): Flow<List<Movie>>

    fun setFavoriteMovies(movie: Movie, state: Boolean)

}
