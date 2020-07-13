package com.example.danielhorowitz.bitcoin.presentation.charts

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.danielhorowitz.bitcoin.R
import com.example.danielhorowitz.bitcoin.domain.model.Chart
import com.example.danielhorowitz.bitcoin.presentation.common.EqualSpacingItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_charts.*
import org.jetbrains.anko.contentView
import org.jetbrains.anko.design.indefiniteSnackbar
import org.jetbrains.anko.dimen
import javax.inject.Inject

@AndroidEntryPoint
class ChartsActivity : AppCompatActivity(), ChartsContract.View {
    @Inject
    lateinit var presenter: ChartsContract.Presenter

    private var adapter: ChartsAdapter? = null
    private val charts = mutableListOf<Chart>()

    override fun showCharts(charts: List<Chart>) {
        this.charts.clear()

        this.charts.addAll(charts)
        adapter?.notifyDataSetChanged()
    }

    override fun showError(throwable: Throwable, tag: String, message: Int) {
        contentView?.indefiniteSnackbar(R.string.unexpected_error, R.string.retry) { presenter.fetchPopularCharts() }
    }

    override fun showLoading() {
        swipeRefreshLayout.isRefreshing = true
    }

    override fun hideLoading() {
        swipeRefreshLayout.isRefreshing = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_charts)
        setupRecycler()
        presenter.fetchPopularCharts()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.destroy()
    }

    private fun setupRecycler() {
        adapter = ChartsAdapter(charts) { presenter.onChartClicked(it) }
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(EqualSpacingItemDecoration(dimen(R.dimen.default_spacing)))
        swipeRefreshLayout.setOnRefreshListener { presenter.fetchPopularCharts() }
    }
}
