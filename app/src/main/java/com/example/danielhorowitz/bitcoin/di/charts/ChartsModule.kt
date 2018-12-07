package com.example.danielhorowitz.bitcoin.di.charts

import android.app.Activity
import com.example.danielhorowitz.bitcoin.Navigator
import com.example.danielhorowitz.bitcoin.data.repository.ChartsRepository
import com.example.danielhorowitz.bitcoin.di.PerActivity
import com.example.danielhorowitz.bitcoin.domain.charts.ChartsInteractor
import com.example.danielhorowitz.bitcoin.domain.charts.ChartsInteractorImpl
import com.example.danielhorowitz.bitcoin.presentation.charts.ChartsActivity
import com.example.danielhorowitz.bitcoin.presentation.charts.ChartsContract
import com.example.danielhorowitz.bitcoin.presentation.charts.ChartsPresenter
import dagger.Binds
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import javax.inject.Named


@Module
abstract class ChartsModule {
    @Binds
    @PerActivity
    internal abstract fun provideView(activity: ChartsActivity): ChartsContract.View

    @Binds
    @PerActivity
    internal abstract fun provideActivity(activity: ChartsActivity): Activity

    @Module
    companion object {
        @Provides
        @PerActivity
        @JvmStatic
        internal fun provideInteractor(chartsRepository: ChartsRepository): ChartsInteractor = ChartsInteractorImpl(chartsRepository)

        @Provides
        @PerActivity
        @JvmStatic
        internal fun providePresenter(view: ChartsContract.View,
                                      navigator: Navigator,
                                      interactor: ChartsInteractor,
                                      @Named("observeOn") observeOn: Scheduler,
                                      @Named("subscribeOn") subscribeOn: Scheduler): ChartsContract.Presenter =
            ChartsPresenter(
                view,
                interactor,
                navigator,
                observeOn,
                subscribeOn
            )
    }
}
