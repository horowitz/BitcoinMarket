package com.example.danielhorowitz.bitcoin.presentation.charts

import com.example.danielhorowitz.bitcoin.Navigator
import com.example.danielhorowitz.bitcoin.domain.charts.ChartsInteractor
import com.example.danielhorowitz.bitcoin.domain.model.Chart
import com.example.danielhorowitz.bitcoin.presentation.common.RxPresenter
import io.reactivex.Scheduler
import io.reactivex.rxkotlin.subscribeBy

class ChartsPresenter(
    private val view: ChartsContract.View,
    private val interactor: ChartsInteractor,
    private val navigator: Navigator,
    observeOn: Scheduler,
    subscribeOn: Scheduler
) : RxPresenter(observeOn, subscribeOn), ChartsContract.Presenter {

    override fun onChartClicked(chart: Chart) {
        navigator.navigateToChartDetails(chart)
    }

    override fun fetchPopularCharts() {
        view.showLoading()

        disposable = interactor.fetchPopularCharts()
            .observeOn(observeOn)
            .subscribeOn(subscribeOn)
            .subscribeBy(
                onSuccess = { charts ->
                    view.hideLoading()
                    view.showCharts(charts)
                },
                onError = { error ->
                    view.hideLoading()
                    view.showError(error)
                }
            )
    }
}