package com.bayuokta.movieapp.di

import com.bayuokta.core.domain.usecase.MovieInteractor
import com.bayuokta.core.domain.usecase.MovieUseCase
import com.bayuokta.movieapp.detail.DetailMovieViewModel
import com.bayuokta.movieapp.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get())}
    viewModel { DetailMovieViewModel(get()) }
}