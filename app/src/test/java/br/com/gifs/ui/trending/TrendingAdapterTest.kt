package br.com.gifs.ui.trending

import br.com.gifs.GifMock
import io.mockk.every
import io.mockk.spyk
import io.mockk.verify
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class TrendingAdapterTest {

    private lateinit var adapter: TrendingAdapter

    @Before
    fun setup() {
        adapter = spyk(TrendingAdapter())
        every { adapter.notifyDataSetChanged() } returns Unit
    }

    @Test
    fun shouldReturnGifListSizeWhenAdapterItemCountIsCalled() {
        adapter.updateGifs(GifMock.getTrendingData())
        verify { adapter.notifyDataSetChanged() }

        assertEquals(3, adapter.itemCount)
    }

    @Test
    fun shouldReturnGifEmptyListSizeWhenAdapterItemCountIsCalled() {
        adapter.updateGifs(GifMock.getEmptyTrendingData())
        verify { adapter.notifyDataSetChanged() }

        assertEquals(0, adapter.itemCount)
    }

}