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

        coEvery { repositoryMock.getTrending(any()) } returns Response.success(trendingResponse)

        viewModel.getTrending()

        assertEquals(expectedTrendingResponseState, viewModel.viewState.value)
    }

    @Test
    fun shouldReturnEmptyResultWhenViewModelExposeEmptyListViewState() {
        val trendingResponse = TrendingResponse(GifMock.getEmptyTrendingData())
        val expectedTrendingResponseState = TrendingViewState.EmptyTrendingList

        coEvery { repositoryMock.getTrending(any()) } returns Response.success(trendingResponse)

        viewModel.getTrending()

        assertEquals(expectedTrendingResponseState, viewModel.viewState.value)
    }

    @Test
    fun shouldReturnErrorWhenViewModelExposeErrorViewState() {
        val mockBody = "{\"message\":\"Something went wrong\"}".toResponseBody("application/json".toMediaTypeOrNull())
        val error = Response.error<TrendingResponse>(401, mockBody)
        val expectedTrendingResponseState = TrendingViewState.Error

        coEvery { repositoryMock.getTrending(any()) } returns error

        viewModel.getTrending()

        assertEquals(expectedTrendingResponseState, viewModel.viewState.value)
    }

    @Test
    fun shouldReturnTrendingSearchResponseResultWhenViewModelExposeTrendingListViewState() {
        val trendingResponse = TrendingResponse(GifMock.getTrendingData())
        val expectedTrendingResponseState = TrendingViewState.TrendingList(trendingResponse.data)

        coEvery { repositoryMock.getTrendingSearch(any(), any()) } returns Response.success(trendingResponse)

        viewModel.getTrendingSearch(QUERY)

        assertEquals(expectedTrendingResponseState, viewModel.viewState.value)
    }

    @Test
    fun shouldReturnEmptySearchResultWhenViewModelExposeEmptyListViewState() {
        val trendingResponse = TrendingResponse(GifMock.getEmptyTrendingData())
        val expectedTrendingResponseState = TrendingViewState.EmptyTrendingList

        coEvery { repositoryMock.getTrendingSearch(any(), any()) } returns Response.success(trendingResponse)

        viewModel.getTrendingSearch(QUERY)

        assertEquals(expectedTrendingResponseState, viewModel.viewState.value)
    }

    @Test
    fun shouldReturnErrorSearchWhenViewModelExposeErrorViewState() {
        val mockBody = "{\"message\":\"Something went wrong\"}".toResponseBody("application/json".toMediaTypeOrNull())
        val error = Response.error<TrendingResponse>(401, mockBody)
        val expectedTrendingResponseState = TrendingViewState.Error

        coEvery { repositoryMock.getTrendingSearch(any(), any()) } returns error

        viewModel.getTrendingSearch(QUERY)

        assertEquals(expectedTrendingResponseState, viewModel.viewState.value)
    }

    @Test
    fun shouldReturnValidateSizeTrendingResponseResultWhenViewModelExposeTrendingListViewState() {
        val trendingResponse = TrendingResponse(GifMock.getTrendingData())
        val expectedTrendingResponseState = TrendingViewState.TrendingList(trendingResponse.data)

        coEvery { repositoryMock.getTrending(any()) } returns Response.success(trendingResponse)

        viewModel.getTrending()

        assertEquals(3, expectedTrendingResponseState.trendingList.size)
    }

    @Test
    fun shouldReturnValidateInformationsTrendingResponseResultWhenViewModelExposeTrendingListViewState() {
        val trendingResponse = TrendingResponse(GifMock.getTrendingData())
        val expectedTrendingResponseState = TrendingViewState.TrendingList(trendingResponse.data)

        coEvery { repositoryMock.getTrending(any()) } returns Response.success(trendingResponse)

        viewModel.getTrending()

        val trendingList = expectedTrendingResponseState.trendingList
        assertEquals("1", trendingList[0].id)
        assertEquals("First Gif", trendingList[0].title)
        assertEquals("2020-11-03", trendingList[0].trending_datetime)
        assertEquals("http://url.com/gif.gif", trendingList[0].images?.original?.url)
        assertEquals("415256426724653", trendingList[0].images?.original?.hash)
    }

    @Test
    fun shouldReturnTrendingResponseInformationsResultWhenViewModelExposeTrendingListViewState() {
        val trendingResponse = GifMock.getTrendingResponse()

        coEvery { repositoryMock.getTrending(any()) } returns Response.success(trendingResponse)

        viewModel.getTrending()

        assertEquals(3, trendingResponse.data.size)
        assertEquals(100, trendingResponse.pagination?.total_count)
        assertEquals(10, trendingResponse.pagination?.count)
        assertEquals(5, trendingResponse.pagination?.offset)
        assertEquals(200, trendingResponse.meta?.status)
        assertEquals("Message", trendingResponse.meta?.msg)
        assertEquals("415256426724653", trendingResponse.meta?.response_id)
    }

    companion object {
        private const val QUERY = "q"
    }

}