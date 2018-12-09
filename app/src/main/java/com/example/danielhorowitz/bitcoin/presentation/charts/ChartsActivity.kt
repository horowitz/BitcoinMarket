package com.example.danielhorowitz.bitcoin.presentation.charts

import android.app.Activity
import android.os.Bundle
import com.example.danielhorowitz.bitcoin.Navigator
import com.example.danielhorowitz.bitcoin.R
import com.example.danielhorowitz.bitcoin.domain.model.Chart
import com.example.danielhorowitz.bitcoin.presentation.common.EqualSpacingItemDecoration
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_charts.*
import org.jetbrains.anko.contentView
import org.jetbrains.anko.design.indefiniteSnackbar
import org.jetbrains.anko.dimen
import javax.inject.Inject

class ChartsActivity : Activity(), ChartsContract.View {
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
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_charts)
        setupRecycler()
        val extraCharts = readExtraCharts(savedInstanceState)
        extraCharts?.let { showCharts(it) } ?: run { presenter.fetchPopularCharts() }

    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.destroy()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putParcelableArrayList(Navigator.CHART_EXTRA, ArrayList(charts))
    }

    private fun readExtraCharts(savedInstanceState: Bundle?): List<Chart>? =
        savedInstanceState?.getParcelableArrayList(Navigator.CHART_EXTRA)

    private fun setupRecycler() {
        adapter = ChartsAdapter(charts) { presenter.onChartClicked(it) }
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(EqualSpacingItemDecoration(dimen(R.dimen.default_spacing)))
        swipeRefreshLayout.setOnRefreshListener { presenter.fetchPopularCharts() }
    }
}