package br.com.gifs.ui.favourite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.gifs.R
import br.com.gifs.data.local.entity.GifEntity
import br.com.gifs.util.ImageUtil
import kotlinx.android.synthetic.main.item_favourite_gif.view.*

class FavouriteAdapter : RecyclerView.Adapter<FavouriteAdapter.FavouriteViewHolder>() {

    private val gifs = mutableListOf<GifEntity>()

    fun updateGifs(gifs: List<GifEntity>) {
        this.gifs.addAll(gifs)
        notifyDataSetChanged()
    }

    fun clearGifs() {
        this.gifs.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_favourite_gif, parent, false)
        return FavouriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavouriteViewHolder, position: Int) {
        holder.onBind(gifs[position])
    }

    override fun getItemCount() = gifs.size

    inner class FavouriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun onBind(gif: GifEntity) {
            gif.url.let {
                ImageUtil.loadImage(itemView.img_favourite_gif, it)
            }
        }

    }

}