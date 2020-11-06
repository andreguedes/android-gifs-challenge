package br.com.gifs.data.source

import android.content.Context
import androidx.room.Room
import br.com.gifs.data.local.AppDatabase

object DatabaseClient {

    private const val DB_NAME = "gifDB"

    fun getDatabase(applicationContext: Context): AppDatabase =
        Room.databaseBuilder(
            applicationContext, AppDatabase::class.java, DB_NAME
        ).build()

}