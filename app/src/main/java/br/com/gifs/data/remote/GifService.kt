package br.com.gifs.data.remote

import br.com.gifs.data.source.RetrofitClient

open class GifService {

    fun getService(): GifApi {
        return RetrofitClient.getClient()
    }

}