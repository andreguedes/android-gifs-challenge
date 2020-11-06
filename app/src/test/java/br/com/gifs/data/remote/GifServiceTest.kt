package br.com.gifs.data.remote

import br.com.gifs.data.source.RetrofitClient
import io.mockk.every
import io.mockk.mockk
import junit.framework.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class GifServiceTest {

    private lateinit var gifService: GifService
    private val retrofitMock = mockk<RetrofitClient>()
    private val apiMock = mockk<GifApi>()

    @Before
    fun setup() {
        gifService = GifService(retrofitMock)
        every { retrofitMock.getClient() } returns apiMock
    }

    @Test
    fun shouldValidateInstanceOfGitService() {
        val service = gifService.getService()
        assertTrue(service is GifApi)
    }

}