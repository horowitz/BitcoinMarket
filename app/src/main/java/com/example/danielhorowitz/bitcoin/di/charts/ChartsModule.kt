package com.example.danielhorowitz.bitcoin.di.charts

import android.app.Activity
import com.example.danielhorowitz.bitcoin.Navigator
import com.example.danielhorowitz.bitcoin.NavigatorImpl
import com.example.danielhorowitz.bitcoin.data.repository.ChartsRepository
import com.example.danielhorowitz.bitcoin.domain.charts.ChartsInteractor
import com.example.danielhorowitz.bitcoin.domain.charts.ChartsInteractorImpl
import com.example.danielhorowitz.bitcoin.domain.mapper.BlockchainChartMapper
import com.example.danielhorowitz.bitcoin.domain.mapper.ChartMapper
import com.example.danielhorowitz.bitcoin.presentation.charts.ChartsContract
import com.example.danielhorowitz.bitcoin.presentation.charts.ChartsPresenter
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import io.reactivex.Scheduler
import javax.inject.Named


@Module
@InstallIn(ActivityComponent::class)
abstract class ChartsModule {
    companion object {
        @Provides
        internal fun provideView(activity: Activity): ChartsContract.View = activity as ChartsContract.View

        @Provides
        @JvmStatic
        internal fun provideMapper(): ChartMapper = BlockchainChartMapper()

        @Provides
        @JvmStatic
        internal fun provideNavigator(activity: Activity): Navigator = NavigatorImpl(activity)

        @Provides
        @JvmStatic
        internal fun provideInteractor(
            chartsRepository: ChartsRepository,
            chartMapper: ChartMapper
        ): ChartsInteractor =
            ChartsInteractorImpl(chartsRepository, chartMapper)

        @Provides
        @JvmStatic
        internal fun providePresenter(
            view: ChartsContract.View,
            navigator: Navigator,
            interactor: ChartsInteractor,
            @Named("observeOn") observeOn: Scheduler,
            @Named("subscribeOn") subscribeOn: Scheduler
        ): ChartsContract.Presenter =
            ChartsPresenter(
                view,
                interactor,
                navigator,
                observeOn,
                subscribeOn
            )
    }

}
