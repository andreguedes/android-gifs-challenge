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
import org.koin.androidx.viewmodel.ext.android.viewModel

class TrendingFragment : Fragment() {

    private val trendingViewModel: TrendingViewModel by viewModel()
    private lateinit var trendingAdapter: TrendingAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_trending, container, false)

    override fun onResume() {
        super.onResume()

        initUI()
        initData()
    }

    private fun initUI() {
        trendingAdapter = TrendingAdapter()

        rv_gifs.apply {
            itemAnimator = DefaultItemAnimator()
            this.layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = trendingAdapter
        }

        btn_try_again.setOnClickListener {
            initData()
        }
    }

    private fun initData() {
        clearMessage()

        trendingViewModel.getTrending()
        trendingViewModel.viewState.observe(this, {
            when (it) {
                is TrendingViewState.TrendingList -> trendingAdapter.updateGifs(it.trendingList)
                is TrendingViewState.EmptyTrendingList -> showEmptyResultMessage()
                else -> showErrorMessage()
            }
            hideProgress()
        })
    }

    private fun showEmptyResultMessage() {
        txt_result_message.text = getString(R.string.no_gifs_found)
        txt_result_message.visibility = View.VISIBLE
    }

    private fun showErrorMessage() {
        txt_result_message.text = getString(R.string.something_went_wrong)
        txt_result_message.visibility = View.VISIBLE
        btn_try_again.visibility = View.VISIBLE
    }

    private fun clearMessage() {
        txt_result_message.visibility = View.GONE
        btn_try_again.visibility = View.GONE
    }

    private fun hideProgress() {
        pgb_trending.visibility = View.GONE
    }

}