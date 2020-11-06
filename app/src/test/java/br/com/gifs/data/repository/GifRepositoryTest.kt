package br.com.gifs.data.repository

import br.com.gifs.GifMock
import br.com.gifs.data.remote.GifApi
import br.com.gifs.data.remote.GifService
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class GifRepositoryTest {

    private lateinit var gifRepository: GifRepository
    private val serviceMock = mockk<GifService>()
    private val gifApiMock = mockk<GifApi>()

    @Before
    fun setup() {
        gifRepository = GifRepository(serviceMock)

        every { serviceMock.getService() } returns gifApiMock
    }

    @Test
    fun shouldReturnTrendingResponseWhenRepositoryExposeResponseObject() = runBlockingTest {
        val trendingResponse = GifMock.getTrendingResponse()
        val expectedResponse = Response.success(trendingResponse)

        coEvery { gifApiMock.getTrending("q") } returns expectedResponse

        val currentResponse = gifRepository.getTrending("q")
        assertEquals(expectedResponse, currentResponse)
    }

    @Test
    fun shouldReturnTrendingSearchResponseWhenRepositoryExposeResponseObject() = runBlockingTest {
        val trendingResponse = GifMock.getTrendingResponse()
        val expectedResponse = Response.success(trendingResponse)

        coEvery { gifApiMock.getTrendingSearch("query", "q") } returns expectedResponse

        val currentResponse = gifRepository.getTrendingSearch("query", "q")
        assertEquals(expectedResponse, currentResponse)
    }

}