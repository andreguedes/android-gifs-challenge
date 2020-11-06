package br.com.gifs.data.repository

import br.com.gifs.data.local.GifDAO
import br.com.gifs.data.local.DAOService

class DAORepository(
    private val daoService: DAOService
) {

    private val dao: GifDAO by lazy {
        daoService.getService().gifDAO()
    }

    fun getAll() = dao.getAll()

}