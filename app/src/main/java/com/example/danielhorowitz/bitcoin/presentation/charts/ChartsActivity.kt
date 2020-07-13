package com.example.danielhorowitz.bitcoin.presentation.charts

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import com.example.danielhorowitz.bitcoin.R
import com.example.danielhorowitz.bitcoin.presentation.addEqualSpacingBetweenItems
import com.example.danielhorowitz.bitcoin.presentation.charts.ChartsActions.*
import com.example.danielhorowitz.bitcoin.presentation.charts.ChartsEvent.NavigateToDetails
import com.example.danielhorowitz.bitcoin.presentation.charts.ChartsState.*
import com.example.danielhorowitz.bitcoin.presentation.details.ChartDetailsActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_charts.*
import org.jetbrains.anko.contentView
import org.jetbrains.anko.design.indefiniteSnackbar

@AndroidEntryPoint
class ChartsActivity : AppCompatActivity(R.layout.activity_charts) {
    private val viewModel: ChartsViewModel by viewModels()
    private val adapter: ChartsAdapter by lazy { ChartsAdapter { viewModel.handle(ChartClicked(it)) } }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupRecycler()

        viewModel.state.observe(this, ::render)
        viewModel.event.observe(this, ::handleEvent)

        viewModel.handle(Start)
    }

    private fun handleEvent(event: ChartsEvent) = when (event) {
        is NavigateToDetails -> startActivity(ChartDetailsActivity.newIntent(this, event.chart))
    }

    private fun render(state: ChartsState) = when (state) {
        Loading -> swipeRefreshLayout.isRefreshing = true
        is Content -> hideLoading().also { adapter.submitList(state.charts) }
        is Error -> hideLoading().also { showError() }
    }

    private fun showError() {
        contentView?.indefiniteSnackbar(R.string.unexpected_error, R.string.retry) {
            viewModel.handle(Refresh)
        }
    }

    private fun setupRecycler() {
        recyclerView.adapter = adapter
        recyclerView.addEqualSpacingBetweenItems()
        swipeRefreshLayout.setOnRefreshListener { viewModel.handle(Refresh) }
    }

    private fun hideLoading() {
        swipeRefreshLayout.isRefreshing = false
    }
}
