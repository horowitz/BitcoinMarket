package com.example.danielhorowitz.bitcoin

import android.app.Activity
import android.app.Application
import com.example.danielhorowitz.bitcoin.di.app.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class BitcoinApplication : Application(), HasActivityInjector {
    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector

    override fun onCreate() {
        super.onCreate()
        initDagger()
    }

    private fun initDagger() {
        DaggerAppComponent
            .builder()
            .application(this)
            .build()
            .inject(this)
    }
}