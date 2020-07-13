package com.example.danielhorowitz.bitcoin.presentation.charts

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import com.example.danielhorowitz.bitcoin.R
import com.example.danielhorowitz.bitcoin.domain.model.Chart
import com.example.danielhorowitz.bitcoin.presentation.charts.ChartsActions.ChartClicked
import com.example.danielhorowitz.bitcoin.presentation.charts.ChartsActions.Start
import com.example.danielhorowitz.bitcoin.presentation.charts.ChartsState.*
import com.example.danielhorowitz.bitcoin.presentation.common.EqualSpacingItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_charts.*
import org.jetbrains.anko.dimen

@AndroidEntryPoint
class ChartsActivity : AppCompatActivity(R.layout.activity_charts) {
    private val viewModel: ChartsViewModel by viewModels()
    private val adapter: ChartsAdapter by lazy { ChartsAdapter { viewModel.handle(ChartClicked(it)) } }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupRecycler()

        viewModel.state.observe(this, ::render)

        viewModel.handle(Start)
    }

    private fun render(state: ChartsState) = when (state) {
        Loading -> swipeRefreshLayout.isRefreshing = true
        is Content -> hideLoading().also { renderContent(state.charts) }
        is Error -> hideLoading().also { showError(state.error) }
    }

    private fun renderContent(charts: List<Chart>) {
        swipeRefreshLayout.isRefreshing = false

        adapter.submitList(charts)
    }

    private fun showError(error: Throwable) {

    }

    private fun setupRecycler() {
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(EqualSpacingItemDecoration(dimen(R.dimen.default_spacing)))
        swipeRefreshLayout.setOnRefreshListener { }
    }

    private fun hideLoading() {
        swipeRefreshLayout.isRefreshing = false
    }
}
