package com.example.danielhorowitz.bitcoin.di.app

import com.example.danielhorowitz.bitcoin.data.network.BlockchainAPI
import com.example.danielhorowitz.bitcoin.data.network.RetrofitAdapter
import com.example.danielhorowitz.bitcoin.data.repository.BlockchainChartNetworkDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
class NetworkModule {
    @Provides
    fun provideDataSource() = BlockchainChartNetworkDataSource(
        RetrofitAdapter.blockchainRetrofit.create(BlockchainAPI::class.java)
    )
}
