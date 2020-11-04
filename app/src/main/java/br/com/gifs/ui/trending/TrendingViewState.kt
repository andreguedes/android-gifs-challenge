package br.com.gifs.ui.trending

import br.com.gifs.data.model.Gif

sealed class TrendingViewState {

    data class TrendingList(val trendingList: List<Gif>): TrendingViewState()
    object EmptyTrendingList: TrendingViewState()
    object Error: TrendingViewState()

}