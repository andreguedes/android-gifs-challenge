package br.com.gifs.data.local

import android.content.Context
import br.com.gifs.data.source.DatabaseClient

open class DAOService(private val applicationContext: Context) {

    fun getService(): AppDatabase = DatabaseClient.getDatabase(applicationContext)

}