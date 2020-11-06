package br.com.gifs.data.local

import br.com.gifs.data.source.DatabaseClient

open class DAOService(private val databaseClient: DatabaseClient) {

    fun getService(): AppDatabase = databaseClient.getDatabase()

}