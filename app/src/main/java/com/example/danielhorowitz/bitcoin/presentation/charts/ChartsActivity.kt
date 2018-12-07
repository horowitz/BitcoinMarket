package com.example.danielhorowitz.bitcoin.presentation.charts

import android.app.Activity
import android.os.Bundle
import com.example.danielhorowitz.bitcoin.R
import com.example.danielhorowitz.bitcoin.domain.model.Chart
import dagger.android.AndroidInjection
import org.jetbrains.anko.contentView
import org.jetbrains.anko.design.indefiniteSnackbar
import javax.inject.Inject

class ChartsActivity : Activity(), ChartsContract.View {
    @Inject
    lateinit var presenter: ChartsContract.Presenter

    override fun showCharts(charts: List<Chart>) {

    }

    override fun showError(throwable: Throwable, tag: String, message: Int) {
        contentView?.indefiniteSnackbar(R.string.unexpected_error, R.string.retry) { presenter.fetchPopularCharts() }
    }

    override fun showLoading() {

    }

    override fun hideLoading() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)

        presenter.fetchPopularCharts()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.destroy()
    }
}