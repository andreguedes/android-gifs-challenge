package br.com.gifs.ui.favourite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.gifs.data.local.entity.GifEntity
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

    fun getGif(id: String) {
        scope.launch {
            daoRepository.getGif(id).collect {
                if (it != null) {
                    state.value = FavouriteViewState.Favourite(it)
                } else {
                    state.value = FavouriteViewState.EmptyFavourite
                }
            }
        }
    }

    fun insertGif(gifEntity: GifEntity) {
        scope.launch {
            state.value = FavouriteViewState.ChangeFavourite
            withContext(Dispatchers.IO) {
                daoRepository.insertGif(gifEntity)
            }
        }
    }

    fun deleteGif(gifEntity: GifEntity) {
        scope.launch {
            state.value = FavouriteViewState.ChangeFavourite
            withContext(Dispatchers.IO) {
                daoRepository.deleteGif(gifEntity)
            }
        }
    }

}