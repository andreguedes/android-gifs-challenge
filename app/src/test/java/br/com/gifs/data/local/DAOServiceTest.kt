package br.com.gifs.data.local

import br.com.gifs.data.source.DatabaseClient
import io.mockk.every
import io.mockk.mockk
import junit.framework.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class DAOServiceTest {

    private lateinit var daoService: DAOService
    private val databaseMock = mockk<DatabaseClient>()
    private val appDatabaseMock = mockk<AppDatabase>()

    @Before
    fun setup() {
        daoService = DAOService(databaseMock)
        every { databaseMock.getDatabase() } returns appDatabaseMock
    }

    @Test
    fun shouldValidateInstanceOfAppDatabaseService() {
        val service = daoService.getService()
        assertTrue(service is AppDatabase)
    }

}