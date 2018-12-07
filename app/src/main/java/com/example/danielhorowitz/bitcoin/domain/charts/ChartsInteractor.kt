package com.example.danielhorowitz.bitcoin.domain.charts

import com.example.danielhorowitz.bitcoin.data.network.BlockchainConfig
import com.example.danielhorowitz.bitcoin.data.repository.ChartsRepository
import com.example.danielhorowitz.bitcoin.domain.mapper.ChartMapper
import com.example.danielhorowitz.bitcoin.domain.model.Chart
import io.reactivex.Single
import io.reactivex.functions.Function4

interface ChartsInteractor {
    fun fetchPopularCharts(timeSpan: String, rollingAvg: String): Single<List<Chart>>
    fun fetchChart(name: String, timeSpan: String, rollingAvg: String): Single<Chart>
}

class ChartsInteractorImpl(
    private val chartsRepository: ChartsRepository,
    private val chartMapper: ChartMapper
) : ChartsInteractor {
    override fun fetchPopularCharts(timeSpan: String, rollingAvg: String): Single<List<Chart>> {
        val avgBlockSizeObs = buildChartObservable(BlockchainConfig.Charts.AVG_BLOCK_SIZE, timeSpan, rollingAvg)
        val memPoolObs = buildChartObservable(BlockchainConfig.Charts.MEMPOOL_SIZE, timeSpan, rollingAvg)
        val marketPriceObs = buildChartObservable(BlockchainConfig.Charts.MARKET_PRICE, timeSpan, rollingAvg)
        val transactionPerSecondObs =
            buildChartObservable(BlockchainConfig.Charts.TRANSACTION_PER_SECOND, timeSpan, rollingAvg)

        return Single.zip(
            avgBlockSizeObs,
            memPoolObs,
            marketPriceObs,
            transactionPerSecondObs,
            Function4<Chart, Chart, Chart, Chart, List<Chart>> { t1, t2, t3, t4 -> listOf(t1, t2, t3, t4) }
        )
    }

    override fun fetchChart(name: String, timeSpan: String, rollingAvg: String): Single<Chart> =
        buildChartObservable(name, timeSpan, rollingAvg)

    private fun buildChartObservable(
        name: String,
        timeSpan: String,
        rollingAvg: String
    ) = chartsRepository.fetchChart(name, timeSpan, rollingAvg).map { chartMapper.mapBlockchainChart(it) }
}