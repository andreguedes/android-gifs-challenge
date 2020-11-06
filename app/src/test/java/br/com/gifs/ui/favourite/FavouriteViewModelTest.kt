package br.com.gifs.ui.favourite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.gifs.GifMock
import br.com.gifs.data.local.AppDatabase
import br.com.gifs.data.local.DAOService
import br.com.gifs.data.local.GifDAO
import br.com.gifs.data.repository.DAORepository
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class FavouriteViewModelTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val repositoryMock = mockk<DAORepository>()
    private val viewModel = FavouriteViewModel(repositoryMock, Dispatchers.Unconfined)
    private val serviceMock = mockk<DAOService>()
    private val databaseMock = mockk<AppDatabase>()
    private val gifDAOMock = mockk<GifDAO>()

    @Before
    fun setup() {
        every { serviceMock.getService() } returns databaseMock
        every { databaseMock.gifDAO() } returns gifDAOMock
    }

    @Test
    fun shouldReturnFavouriteListWhenViewModelExposeFavouriteListViewState() = runBlockingTest {
        val gifList = GifMock.getGifListEntityData()
        val response = flow { emit(gifList) }
        val expectedFavouriteState = FavouriteViewState.FavouriteList(gifList)

        coEvery { repositoryMock.getAll() } returns response

        viewModel.getFavouriteList()

        assertEquals(expectedFavouriteState, viewModel.viewState.value)
    }

    @Test
    fun shouldReturnEmptyListWhenViewModelExposeEmptyListViewState() = runBlockingTest {
        val response = flow { emit(GifMock.getEmptyGifData()) }
        val expectedFavouriteState = FavouriteViewState.EmptyFavouriteList

        coEvery { repositoryMock.getAll() } returns response

        viewModel.getFavouriteList()

        assertEquals(expectedFavouriteState, viewModel.viewState.value)
    }

    @Test
    fun shouldReturnFavouriteWhenViewModelExposeFavouriteViewState() = runBlockingTest {
        val gifEntity = GifMock.getGifData("1")
        val response = flow { emit(gifEntity) }
        val expectedFavouriteState = FavouriteViewState.Favourite(gifEntity)

        coEvery { repositoryMock.getGif("1") } returns response

        viewModel.getGif("1")

        assertEquals(expectedFavouriteState, viewModel.viewState.value)
    }

    @Test
    fun shouldReturnFavouriteWhenViewModelExposeFavouriteObject() = runBlockingTest {
        val gifEntity = GifMock.getGifData("3")
        val response = flow { emit(gifEntity) }
        val expectedFavouriteState = FavouriteViewState.Favourite(gifEntity)

        coEvery { repositoryMock.getGif("3") } returns response

        viewModel.getGif("3")

        assertEquals(gifEntity, expectedFavouriteState.favourite)
    }

    @Test
    fun shouldReturnFavouriteWithInformationsWhenViewModelExposeFavouriteObject() = runBlockingTest {
        val gifEntity = GifMock.getGifData("2")
        val response = flow { emit(gifEntity) }
        val expectedFavouriteState = FavouriteViewState.Favourite(gifEntity)

        coEvery { repositoryMock.getGif("2") } returns response

        viewModel.getGif("2")

        assertEquals("2", expectedFavouriteState.favourite?.id)
        assertEquals("Second Gif", expectedFavouriteState.favourite?.title)
        assertEquals("http://second.com/img", expectedFavouriteState.favourite?.url)
    }

    @Test
    fun shouldReturnFavouriteEmptyWhenViewModelExposeFavouriteObject() = runBlockingTest {
        val gifEntity = GifMock.getGifData("4")
        val response = flow { emit(gifEntity) }
        val expectedFavouriteState = FavouriteViewState.EmptyFavourite

        coEvery { repositoryMock.getGif("4") } returns response

        viewModel.getGif("4")

        assertEquals(expectedFavouriteState, viewModel.viewState.value)
    }

    @Test
    fun shouldReturnChangeFavouriteWhenViewModelExposeFavouriteInsertObject() = runBlockingTest {
        val gifEntity = GifMock.getGifData("3")
        val expectedFavouriteState = FavouriteViewState.ChangeFavourite

        viewModel.insertGif(gifEntity!!)

        assertEquals(expectedFavouriteState, viewModel.viewState.value)
    }

    @Test
    fun shouldReturnChangeFavouriteWhenViewModelExposeFavouriteDeleteObject() = runBlockingTest {
        val gifEntity = GifMock.getGifData("3")
        val expectedFavouriteState = FavouriteViewState.ChangeFavourite

        viewModel.deleteGif(gifEntity!!)

        assertEquals(expectedFavouriteState, viewModel.viewState.value)
    }

}