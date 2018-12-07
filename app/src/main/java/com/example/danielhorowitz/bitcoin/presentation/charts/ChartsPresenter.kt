package com.example.danielhorowitz.bitcoin.presentation.charts

import com.example.danielhorowitz.bitcoin.Navigator
import com.example.danielhorowitz.bitcoin.data.network.BlockchainConfig
import com.example.danielhorowitz.bitcoin.domain.charts.ChartsInteractor
import com.example.danielhorowitz.bitcoin.domain.model.Chart
import com.example.danielhorowitz.bitcoin.presentation.common.RxPresenter
import io.reactivex.Scheduler

class ChartsPresenter(
    private val view: ChartsContract.View,
    private val interactor: ChartsInteractor,
    private val navigator: Navigator,
    observeOn: Scheduler,
    subscribeOn: Scheduler
) : RxPresenter(observeOn, subscribeOn), ChartsContract.Presenter {

    override fun onChartClicked(chart: Chart) {

    }

    override fun fetchPopularCharts() {
        view.showLoading()

        val timesSpan = "5${BlockchainConfig.Params.Values.WEEK}"
        val rollingAvg = "8${BlockchainConfig.Params.Values.HOURS}"
        disposable = interactor.fetchPopularCharts(timesSpan, rollingAvg)
            .observeOn(observeOn)
            .subscribeOn(subscribeOn)
            .subscribe({ charts ->
                view.hideLoading()
                view.showCharts(charts)
            }, { error ->
                view.hideLoading()
                view.showError(error)
            })
    }


}