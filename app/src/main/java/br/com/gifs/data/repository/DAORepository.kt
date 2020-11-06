package br.com.gifs.data.repository

import br.com.gifs.data.local.GifDAO
import br.com.gifs.data.local.DAOService
import br.com.gifs.data.local.entity.GifEntity

class DAORepository(
    private val daoService: DAOService
) {

    private val dao: GifDAO by lazy {
        daoService.getService().gifDAO()
    }

    fun getAll() = dao.getAll()

    fun getGif(id: String) = dao.getGif(id)

    suspend fun insertGif(gifEntity: GifEntity) = dao.insert(gifEntity)

    suspend fun deleteGif(gifEntity: GifEntity) = dao.delete(gifEntity)

}