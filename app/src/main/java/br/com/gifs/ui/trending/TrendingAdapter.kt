package br.com.gifs.ui.trending

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.gifs.R
import br.com.gifs.data.model.Gif
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_gif.view.*

class TrendingAdapter : RecyclerView.Adapter<TrendingAdapter.TrendingViewHolder>() {

    private val gifs = arrayListOf(
        Gif("1", "Gif1"),
        Gif("1", "Gif1"),
        Gif("1", "Gif1"),
        Gif("1", "Gif1"),
        Gif("1", "Gif1"),
        Gif("1", "Gif1"),
        Gif("1", "Gif1"),
        Gif("1", "Gif1"),
        Gif("1", "Gif1"),
        Gif("1", "Gif1"),
        Gif("1", "Gif1"),
        Gif("1", "Gif1"),
        Gif("1", "Gif1"),
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_gif, parent, false)
        return TrendingViewHolder(view)
    }

    override fun onBindViewHolder(holder: TrendingViewHolder, position: Int) {
        holder.onBind(gifs[position])
    }

    override fun getItemCount() = gifs.size

    inner class TrendingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun onBind(gif: Gif) {
            with(itemView) {
                Glide.with(itemView.context).asGif().load("https://media.giphy.com/media/xUOxfolJrVBce4RNAI/giphy.gif").into(img_gif)
            }
        }

    }

}