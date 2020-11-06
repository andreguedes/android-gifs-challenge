package br.com.gifs

import br.com.gifs.data.local.entity.GifEntity
import br.com.gifs.data.model.Gif
import br.com.gifs.data.model.Images
import br.com.gifs.data.model.Original

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

    fun getGifListEntityData(): List<GifEntity> {
        return listOf(
            GifEntity("1", "First Gif", "http://first.com/img"),
            GifEntity("2", "Second Gif", "http://second.com/img"),
            GifEntity("3", "Third Gif", "http://third.com/img")
        )
    }

    fun getEmptyGifData() = listOf<GifEntity>()

}