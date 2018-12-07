package com.example.danielhorowitz.bitcoin.presentation.charts

import com.example.danielhorowitz.bitcoin.Navigator
import com.example.danielhorowitz.bitcoin.domain.charts.ChartsInteractor
import com.example.danielhorowitz.bitcoin.presentation.common.RxPresenter
import io.reactivex.Scheduler

class ChartsPresenter(
    private val view: ChartsContract.View,
    private val interactor: ChartsInteractor,
    private val navigator: Navigator,
    observeOn: Scheduler,
    subscribeOn: Scheduler
) : RxPresenter(observeOn, subscribeOn), ChartsContract.Presenter {


}