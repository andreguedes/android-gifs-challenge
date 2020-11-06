package br.com.gifs.data.source

import br.com.gifs.GifApplication
import br.com.gifs.data.local.AppDatabase
import io.mockk.mockk
import org.junit.Before
import org.junit.Test

class DatabaseClientTest {

    private lateinit var databaseClient: DatabaseClient
    private val applicatonMock = mockk<GifApplication>()

    @Before
    fun setup() {
        databaseClient = DatabaseClient(applicatonMock)
    }

    @Test
    fun shouldValidateInstanceOfDatabase() {
        val database = databaseClient.getDatabase()
        assert(database is AppDatabase)
    }

}