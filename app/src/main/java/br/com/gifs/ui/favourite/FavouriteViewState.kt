package br.com.gifs.ui.favourite

import br.com.gifs.data.local.entity.GifEntity

sealed class FavouriteViewState {

    data class FavouriteList(val favouriteList: List<GifEntity>): FavouriteViewState()
    data class Favourite(val favourite: GifEntity): FavouriteViewState()
    object EmptyFavouriteList: FavouriteViewState()

}