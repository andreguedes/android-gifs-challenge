package br.com.gifs.data.local

import androidx.room.*
import br.com.gifs.data.local.entity.GifEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GifDAO {

    @Query("SELECT * FROM gifentity")
    fun getAll(): Flow<List<GifEntity>>

    @Query("SELECT * FROM gifentity WHERE id = :id")
    fun getGif(id: String): Flow<GifEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(gif: GifEntity): Void

    @Delete
    suspend fun delete(gif: GifEntity): Void

}