package br.com.gifs.ui.favourite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.gifs.R
import kotlinx.android.synthetic.main.fragment_trending.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavouriteFragment : Fragment() {

    private val favouriteViewModel: FavouriteViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_favourite, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initUI()
        initData()
    }

    private fun initUI() {

    }

    private fun initData() {
        favouriteViewModel.getFavouriteList()
        favouriteViewModel.viewState.observe(this, {
            when (it) {
                is FavouriteViewState.FavouriteList -> {

                }
                else -> showEmptyResultMessage()
            }
        })
    }

    private fun showEmptyResultMessage() {
        txt_result_message.text = getString(R.string.no_gifs_found)
        txt_result_message.visibility = View.VISIBLE
    }

}