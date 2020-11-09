package br.com.gifs.ui.trending

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.gifs.R
import br.com.gifs.data.model.Gif
import br.com.gifs.util.ImageUtil
import kotlinx.android.synthetic.main.item_trending_gif.view.*

class TrendingAdapter : RecyclerView.Adapter<TrendingAdapter.TrendingViewHolder>() {

    private val gifs = mutableListOf<Gif>()

    fun updateGifs(gifs: List<Gif>) {
        this.gifs.addAll(gifs)
        notifyDataSetChanged()
    }

    fun clearGifs() {
        this.gifs.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_trending_gif, parent, false)
        return TrendingViewHolder(view)
    }

    override fun onBindViewHolder(holder: TrendingViewHolder, position: Int) {
        holder.onBind(gifs[position])
    }

    override fun getItemCount() = gifs.size

    inner class TrendingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun onBind(gif: Gif) {
            gif.images?.original?.let {
                ImageUtil.loadImage(itemView.img_trending_gif, it.url)
            }
        }

    }

}