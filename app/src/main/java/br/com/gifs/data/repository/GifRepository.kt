package br.com.gifs.data.repository

import br.com.gifs.data.model.response.TrendingResponse
import br.com.gifs.data.remote.GifService
import retrofit2.Response

class GifRepository(private val gifService: GifService) {

    suspend fun getTrending(rating: String): Response<TrendingResponse> {
        return gifService.getService().getTrending(rating)
    }

}