package br.com.gifs.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.gifs.data.local.entity.GifEntity

@Database(entities = [GifEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun gifDAO(): GifDAO

}