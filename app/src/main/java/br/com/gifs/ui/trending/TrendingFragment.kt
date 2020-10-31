package br.com.gifs.ui.trending

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import br.com.gifs.R
import kotlinx.android.synthetic.main.fragment_trending.*

class TrendingFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_trending, container, false)

    override fun onResume() {
        super.onResume()

        initGifs()
    }

    private fun initGifs() {
        rv_gifs.apply {
            itemAnimator = DefaultItemAnimator()
            this.layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = TrendingAdapter()
        }
    }

}