package br.com.gifs

import br.com.gifs.data.local.entity.GifEntity
import br.com.gifs.data.model.*
import br.com.gifs.data.model.response.TrendingResponse

object GifMock {

    fun getTrendingData(): List<Gif> {
        val original = Original("http://url.com/gif.gif", "415256426724653")
        val images = Images(original)
        return listOf(
            Gif("1", "First Gif", "2020-11-03", images),
            Gif("2", "Second Gif", "2020-11-04", images),
            Gif("3", "Third Gif", "2020-11-05", images)
        )
    }

    fun getEmptyTrendingData() = listOf<Gif>()

    fun getTrendingResponse(): TrendingResponse {
        val pagination = Pagination(100, 10, 5)
        val meta = Meta(200, "Message", "415256426724653")
        return TrendingResponse(
            getTrendingData(),
            pagination,
            meta
        )
    }

    fun getGifListEntityData(): List<GifEntity> {
        return listOf(
            GifEntity("1", "First Gif", "http://first.com/img"),
            GifEntity("2", "Second Gif", "http://second.com/img"),
            GifEntity("3", "Third Gif", "http://third.com/img")
        )
    }

    fun getEmptyGifData() = listOf<GifEntity>()

    fun getGifData(id: String): GifEntity? = getGifListEntityData().find { it.id == id }

}