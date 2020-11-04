package br.com.gifs.di

import br.com.gifs.data.remote.GifService
import br.com.gifs.data.repository.GifRepository
import br.com.gifs.ui.trending.TrendingViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val gifModule = module {
    factory { GifRepository(get()) }
    single { GifService() }
    viewModel { TrendingViewModel(gifRepository = get()) }
}