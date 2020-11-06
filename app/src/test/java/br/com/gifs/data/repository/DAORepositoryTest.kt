package br.com.gifs.data.repository

import br.com.gifs.GifMock
import br.com.gifs.data.local.AppDatabase
import br.com.gifs.data.local.DAOService
import br.com.gifs.data.local.GifDAO
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.flow
import org.junit.Before
import org.junit.Test

class DAORepositoryTest {

    private lateinit var daoRepository: DAORepository
    private val serviceMock = mockk<DAOService>()
    private val databaseMock = mockk<AppDatabase>()
    private val gifDAOMock = mockk<GifDAO>()

    @Before
    fun setup() {
        daoRepository = DAORepository(serviceMock)

        every { serviceMock.getService() } returns databaseMock
        every { databaseMock.gifDAO() } returns gifDAOMock
    }

    @Test
    fun shouldValidateGifEntityWhenGifDAOExposeGetAllResponse() {
        val gifListEntityData = GifMock.getGifListEntityData()
        val expectedResponse = flow { emit(gifListEntityData) }

        coEvery { gifDAOMock.getAll() } returns expectedResponse

        val currentResponse = daoRepository.getAll()
        assertEquals(expectedResponse, currentResponse)
    }

    @Test
    fun shouldValidateGifEntityWhenGifDAOExposeGetByIdResponse() {
        val entityData = GifMock.getGifData("1")
        val expectedResponse = flow { emit(entityData) }

        coEvery { gifDAOMock.getGif("1") } returns expectedResponse

        val currentResponse = daoRepository.getGif("1")
        assertEquals(expectedResponse, currentResponse)
    }

}