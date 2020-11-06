package br.com.gifs.data.source

import android.content.Context
import androidx.room.Room
import br.com.gifs.data.local.AppDatabase

class DatabaseClient(
    private val applicationContext: Context
) {

    fun getDatabase(): AppDatabase =
        Room.databaseBuilder(
            applicationContext, AppDatabase::class.java, DB_NAME
        ).build()

    companion object {
        private const val DB_NAME = "gifDB"
    }

}