package br.com.gifs.data.remote

import br.com.gifs.data.source.RetrofitClient

open class GifService(private val retrofitClient: RetrofitClient) {

    fun getService(): GifApi {
        return retrofitClient.getClient()
    }

}