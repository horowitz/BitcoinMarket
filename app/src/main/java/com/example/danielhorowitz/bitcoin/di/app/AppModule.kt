package com.example.danielhorowitz.bitcoin.di.app

import android.content.Context
import com.example.danielhorowitz.bitcoin.BitcoinApplication
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Named


/**
 * Created by danielhorowitz on 8/9/17.
 */
@Module(

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

