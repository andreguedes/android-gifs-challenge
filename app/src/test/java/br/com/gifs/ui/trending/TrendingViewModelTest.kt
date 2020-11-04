package br.com.gifs.ui.trending

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.gifs.GifMock
import br.com.gifs.data.model.response.TrendingResponse
import br.com.gifs.data.repository.GifRepository
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers.Unconfined
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

class TrendingViewModelTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val repositoryMock = mockk<GifRepository>()
    private val viewModel = TrendingViewModel(repositoryMock, Unconfined)

    @Test
    fun shouldReturnTrendingResponseResultWhenViewModelExposeTrendingListViewState() {
        val trendingResponse = TrendingResponse(GifMock.getTrendingData())
        val expectedTrendingResponseState = TrendingViewState.TrendingList(trendingResponse.data)

        coEvery { repositoryMock.getTrending(RATING) } returns Response.success(trendingResponse)

        viewModel.getTrending()

        assertEquals(viewModel.viewState.value, expectedTrendingResponseState)
    }

    @Test
    fun shouldReturnEmptyResultWhenViewModelExposeEmptyListViewState() {
        val trendingResponse = TrendingResponse(GifMock.getEmptyTrendingData())
        val expectedTrendingResponseState = TrendingViewState.EmptyTrendingList

        coEvery { repositoryMock.getTrending(RATING) } returns Response.success(trendingResponse)

        viewModel.getTrending()

        assertEquals(viewModel.viewState.value, expectedTrendingResponseState)
    }

    @Test
    fun shouldReturnErrorWhenViewModelExposeErrorViewState() {
        val mockBody = "{\"message\":\"Something went wrong\"}".toResponseBody("application/json".toMediaTypeOrNull())
        val error = Response.error<TrendingResponse>(401, mockBody)
        val expectedTrendingResponseState = TrendingViewState.Error

        coEvery { repositoryMock.getTrending(RATING) } returns error

        viewModel.getTrending()

        assertEquals(viewModel.viewState.value, expectedTrendingResponseState)
    }

    companion object {
        private const val RATING = "g"
    }

}