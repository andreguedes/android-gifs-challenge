package br.com.gifs.util

import android.widget.ImageView
import com.bumptech.glide.Glide

object ImageUtil {

    fun loadImage(imageView: ImageView, path: String) {
        Glide.with(imageView.context).asGif().load(path).into(imageView)
    }

}