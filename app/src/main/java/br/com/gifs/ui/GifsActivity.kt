package br.com.gifs.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.gifs.R
import br.com.gifs.ui.favourite.FavouriteFragment
import br.com.gifs.ui.trending.TrendingFragment
import kotlinx.android.synthetic.main.activity_gifs.*

class GifsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gifs)

        initViews()
    }

    private fun initViews() {
        view_pager.apply {
            adapter = ViewPagerAdapter(supportFragmentManager).apply {
                addFragment(TrendingFragment(), getString(R.string.trending))
                addFragment(FavouriteFragment(), getString(R.string.favourite))
            }
            tabs.setupWithViewPager(this)
        }
    }

}