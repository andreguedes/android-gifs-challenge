package br.com.gifs.ui.favourite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.gifs.data.repository.DAORepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect

class FavouriteViewModel(private val daoRepository: DAORepository, mainDispatcher: CoroutineDispatcher = Dispatchers.Main) : ViewModel() {

    private val job = SupervisorJob()
    private val state = MutableLiveData<FavouriteViewState>()
    private val scope = CoroutineScope(mainDispatcher + job)
    val viewState: LiveData<FavouriteViewState> = state

    fun getFavouriteList() {
        scope.launch {
            daoRepository.getAll().collect {
                if (it.isNullOrEmpty()) {
                    state.value = FavouriteViewState.EmptyFavouriteList
                } else {
                    state.value = FavouriteViewState.FavouriteList(it)
                }
            }
        }
    }

}