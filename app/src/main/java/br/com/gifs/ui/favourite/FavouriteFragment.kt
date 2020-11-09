package br.com.gifs.ui.favourite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import br.com.gifs.R
import kotlinx.android.synthetic.main.fragment_favourite.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavouriteFragment : Fragment() {

    private val favouriteViewModel: FavouriteViewModel by viewModel()
    private lateinit var favouriteAdapter: FavouriteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_favourite, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initUI()
        initData()
    }

    private fun initUI() {
        favouriteAdapter = FavouriteAdapter()

        rv_favourite_gifs.apply {
            itemAnimator = DefaultItemAnimator()
            this.layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = favouriteAdapter
        }
    }

    private fun initData() {
        clearMessage()

        favouriteViewModel.getFavouriteList()
        favouriteViewModel.viewState.observe(this, {
            favouriteAdapter.clearGifs()
            when (it) {
                is FavouriteViewState.FavouriteList -> favouriteAdapter.updateGifs(it.favouriteList)
                else -> showEmptyResultMessage()
            }
        })
    }

    private fun showEmptyResultMessage() {
        txt_result_message.text = getString(R.string.no_gifs_found)
        txt_result_message.visibility = View.VISIBLE
    }

    private fun clearMessage() {
        txt_result_message.visibility = View.GONE
    }

}