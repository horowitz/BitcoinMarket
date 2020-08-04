package com.example.danielhorowitz.bitcoin.domain.charts

import com.example.danielhorowitz.bitcoin.data.network.BlockchainConfig
import com.example.danielhorowitz.bitcoin.data.repository.BlockchainChartNetworkDataSource
import com.example.danielhorowitz.bitcoin.domain.mapper.toBlockChainChart
import com.example.danielhorowitz.bitcoin.domain.model.Chart
import javax.inject.Inject

class FetchPopularCharts @Inject constructor(private val dataSource: BlockchainChartNetworkDataSource) {
    suspend operator fun invoke(): List<Chart> = BlockchainConfig.PopularCharts.values()
        .map { dataSource.fetchChart(it.id).toBlockChainChart() }
}
