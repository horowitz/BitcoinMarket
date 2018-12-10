package com.example.danielhorowitz.bitcoin.presentation.common

import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable

abstract class RxPresenter(val observeOn: Scheduler,
                           val subscribeOn: Scheduler) : BasePresenter {


    var disposable: Disposable? = null

    override fun destroy() {
        disposable?.dispose()
    }
}
