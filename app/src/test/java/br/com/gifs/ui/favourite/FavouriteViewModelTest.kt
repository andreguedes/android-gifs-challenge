package br.com.gifs.ui.favourite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.gifs.GifMock
import br.com.gifs.data.repository.DAORepository
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test

class FavouriteViewModelTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val repositoryMock = mockk<DAORepository>()
    private val viewModel = FavouriteViewModel(repositoryMock, Dispatchers.Unconfined)

    @Test
    fun shouldReturnFavouriteListWhenViewModelExposeFavouriteListViewState() = runBlocking {
        val gifList = GifMock.getGifListEntityData()
        val response = flow { emit(gifList) }
        val expectedFavouriteState = FavouriteViewState.FavouriteList(gifList)

        coEvery { repositoryMock.getAll() } returns response

        viewModel.getFavouriteList()

        assertEquals(viewModel.viewState.value, expectedFavouriteState)
    }

    @Test
    fun shouldReturnEmptyListWhenViewModelExposeEmptyListViewState() = runBlocking {
        val response = flow { emit(GifMock.getEmptyGifData()) }
        val expectedFavouriteState = FavouriteViewState.EmptyFavouriteList

        coEvery { repositoryMock.getAll() } returns response

        viewModel.getFavouriteList()

        assertEquals(viewModel.viewState.value, expectedFavouriteState)
    }

}