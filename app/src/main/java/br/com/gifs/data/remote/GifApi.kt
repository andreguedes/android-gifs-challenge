package br.com.gifs.data.remote

import br.com.gifs.data.model.response.TrendingResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GifApi {

    @GET("/v1/gifs/trending")
    suspend fun getTrending(
        @Query("rating") rating: String
    ): Response<TrendingResponse>

    @GET("/v1/gifs/search")
    suspend fun getTrendingSearch(
        @Query("q") query: String,
        @Query("rating") rating: String
    ): Response<TrendingResponse>

}