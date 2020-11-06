package br.com.gifs.ui.favourite

import br.com.gifs.GifMock
import io.mockk.every
import io.mockk.spyk
import io.mockk.verify
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class FavouriteAdapterTest {

    private lateinit var adapter: FavouriteAdapter

    @Before
    fun setup() {
        adapter = spyk(FavouriteAdapter())
        every { adapter.notifyDataSetChanged() } returns Unit
    }

    @Test
    fun shouldReturnGifEntityListSizeWhenAdapterItemCountIsCalled() {
        adapter.updateGifs(GifMock.getGifListEntityData())
        verify { adapter.notifyDataSetChanged() }

        assertEquals(3, adapter.itemCount)
    }

    @Test
    fun shouldReturnGifEntityEmptyListSizeWhenAdapterItemCountIsCalled() {
        adapter.updateGifs(GifMock.getEmptyGifData())
        verify { adapter.notifyDataSetChanged() }

        assertEquals(0, adapter.itemCount)
    }

}