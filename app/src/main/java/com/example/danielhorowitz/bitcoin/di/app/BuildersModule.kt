package com.example.danielhorowitz.bitcoin.di.app

import android.app.Activity
import com.example.danielhorowitz.bitcoin.di.charts.ChartsSubComponent
import com.example.danielhorowitz.bitcoin.presentation.charts.ChartsActivity
import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class BuildersModule {
    @Binds
    @IntoMap
    @ActivityKey(ChartsActivity::class)
    abstract fun bindChartsActivityInjectorFactory(builder: ChartsSubComponent.Builder): AndroidInjector.Factory<out Activity>
}