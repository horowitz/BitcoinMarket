package com.example.danielhorowitz.bitcoin.di.app

import android.content.Context
import com.example.danielhorowitz.bitcoin.BitcoinApplication
import com.example.danielhorowitz.bitcoin.di.charts.ChartsSubComponent
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Named

@Module(
    subcomponents = [
        (ChartsSubComponent::class)
    ]
)
class AppModule {

    @Provides
    fun context(application: BitcoinApplication): Context = application.applicationContext

    @Provides
    @Named("observeOn")
    fun observeOnScheduler(): Scheduler = AndroidSchedulers.mainThread()

    @Provides
    @Named("subscribeOn")
    fun subscribeOnScheduler(): Scheduler = Schedulers.io()

}

