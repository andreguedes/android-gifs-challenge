package br.com.gifs.util

import android.widget.ImageView
import br.com.gifs.GifApplication
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.target.ViewTarget
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import org.junit.Before
import org.junit.Test

class ImageUtilTest {

    private val imageViewMock = mockk<ImageView>()
    private val requestManagerMock = mockk<RequestManager>()
    private val requestBuilderGifMock = mockk<RequestBuilder<GifDrawable>>()
    private val requestBuilderLoadMock = mockk<RequestBuilder<GifDrawable>>()
    private val viewTargetMock = mockk<ViewTarget<ImageView, GifDrawable>>()
    private val applicationMock = mockk<GifApplication>()

    @Before
    fun setup() {
        mockkStatic(ImageUtil::class)
        mockkStatic(Glide::class)

        every { imageViewMock.context } returns applicationMock
        every { Glide.with(imageViewMock.context) } returns requestManagerMock
        every { requestManagerMock.asGif() } returns requestBuilderGifMock
        every { requestBuilderGifMock.load(any<String>()) } returns requestBuilderLoadMock
        every { requestBuilderLoadMock.into(imageViewMock) } returns viewTargetMock
    }

    @Test
    fun shouldVerifyMethodsInsideLibraryFromImageUtilLoadImage() {
        ImageUtil.loadImage(imageViewMock, "://somePathHere")
    }

}