package br.com.gifs.di

import br.com.gifs.data.local.DAOService
import br.com.gifs.data.remote.GifService
import br.com.gifs.data.repository.DAORepository
import br.com.gifs.data.repository.GifRepository
import br.com.gifs.data.source.DatabaseClient
import br.com.gifs.data.source.RetrofitClient
import br.com.gifs.ui.favourite.FavouriteViewModel
import br.com.gifs.ui.trending.TrendingViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val gifModule = module {
    factory { GifRepository(get()) }
    factory { DAORepository(get()) }
    factory { RetrofitClient }
    factory { DatabaseClient(get()) }
    single { GifService(get()) }
    single { DAOService(get()) }
    viewModel { TrendingViewModel(gifRepository = get()) }
    viewModel { FavouriteViewModel(daoRepository = get()) }
}