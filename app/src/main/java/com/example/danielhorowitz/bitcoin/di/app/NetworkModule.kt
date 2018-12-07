package com.example.danielhorowitz.bitcoin.di.app

import com.example.danielhorowitz.bitcoin.data.network.BlockchainAPI
import com.example.danielhorowitz.bitcoin.data.network.RetrofitAdapter
import com.example.danielhorowitz.bitcoin.data.repository.BlockchainChartRepository
import com.example.danielhorowitz.bitcoin.data.repository.ChartsRepository
import dagger.Module
import dagger.Provides

@Module
class NetworkModule {
    @Provides
    fun provideChartsRepository(): ChartsRepository = BlockchainChartRepository(
        RetrofitAdapter.blockchainRetrofit.create(BlockchainAPI::class.java)
    )
}