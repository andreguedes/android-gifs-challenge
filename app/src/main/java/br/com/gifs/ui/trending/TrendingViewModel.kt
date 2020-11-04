package br.com.gifs.ui.trending

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.gifs.BuildConfig
import br.com.gifs.data.repository.GifRepository
import kotlinx.coroutines.*

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
            response.let {
                if (it.isSuccessful) {
                    val trendingResponse = it.body()
                    trendingResponse?.data.let {  data ->
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
    }

}