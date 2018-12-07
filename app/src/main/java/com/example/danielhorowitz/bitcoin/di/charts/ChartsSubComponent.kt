package com.example.danielhorowitz.bitcoin.di.charts

import com.example.danielhorowitz.bitcoin.di.ActivityModule
import com.example.danielhorowitz.bitcoin.di.PerActivity
import com.example.danielhorowitz.bitcoin.presentation.charts.ChartsActivity
import dagger.Subcomponent
import dagger.android.AndroidInjector

@PerActivity
@Subcomponent(modules = [(ActivityModule::class), (ChartsModule::class)])
interface ChartsSubComponent : AndroidInjector<ChartsActivity> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<ChartsActivity>()
}