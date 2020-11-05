package br.com.gifs.ui.trending

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.gifs.BuildConfig
import br.com.gifs.data.model.response.TrendingResponse
import br.com.gifs.data.repository.GifRepository
import kotlinx.coroutines.*
import retrofit2.Response

class TrendingViewModel(
    private val gifRepository: GifRepository,
    mainDispatcher: CoroutineDispatcher = Dispatchers.Main
) : ViewModel() {

    private val job = SupervisorJob()
    private val state = MutableLiveData<TrendingViewState>()
    private val scope = CoroutineScope(mainDispatcher + job)
    val viewState: LiveData<TrendingViewState> = state

    fun getTrending() {
        scope.launch {
            val response = gifRepository.getTrending(BuildConfig.RATING)
            handleTrendingResponse(response)
        }
    }

    fun getTrendingSearch(query: String) {
        scope.launch {
            val response = gifRepository.getTrendingSearch(query, BuildConfig.RATING)
            handleTrendingResponse(response)
        }
    }

    private fun handleTrendingResponse(it: Response<TrendingResponse>) {
        if (it.isSuccessful) {
            val trendingResponse = it.body()
            trendingResponse?.data.let { data ->
                if (data.isNullOrEmpty()) {
                    state.value = TrendingViewState.EmptyTrendingList
                } else {
                    state.value = TrendingViewState.TrendingList(data)
                }
            }
        } else {
            state.value = TrendingViewState.Error
        }
    }

}