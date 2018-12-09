package com.example.danielhorowitz.bitcoin.domain.charts

import com.example.danielhorowitz.bitcoin.data.network.BlockchainConfig
import com.example.danielhorowitz.bitcoin.data.repository.ChartsRepository
import com.example.danielhorowitz.bitcoin.domain.mapper.ChartMapper
import com.example.danielhorowitz.bitcoin.domain.model.Chart
import io.reactivex.Single
import io.reactivex.functions.Function4

interface ChartsInteractor {
    fun fetchPopularCharts(): Single<List<Chart>>
    fun fetchChart(name: String, timeSpan: String? = null, rollingAvg: String? = null): Single<Chart>
}

class ChartsInteractorImpl(
    private val chartsRepository: ChartsRepository,
    private val chartMapper: ChartMapper
) : ChartsInteractor {
    override fun fetchPopularCharts(): Single<List<Chart>> {
        val avgBlockSizeObs = buildChartObservable(BlockchainConfig.Charts.AVG_BLOCK_SIZE)
        val memPoolObs = buildChartObservable(BlockchainConfig.Charts.MEMPOOL_SIZE)
        val marketPriceObs = buildChartObservable(BlockchainConfig.Charts.MARKET_PRICE)
        val transactionPerSecondObs =
            buildChartObservable(BlockchainConfig.Charts.TRANSACTION_PER_SECOND)

        return Single.zip(
            avgBlockSizeObs,
            memPoolObs,
            marketPriceObs,
            transactionPerSecondObs,
            Function4<Chart, Chart, Chart, Chart, List<Chart>> { t1, t2, t3, t4 -> listOf(t1, t2, t3, t4) }
        )
    }

    override fun fetchChart(name: String, timeSpan: String?, rollingAvg: String?): Single<Chart> =
        buildChartObservable(name)

    private fun buildChartObservable(name: String) =
        chartsRepository.fetchChart(name).map { chartMapper.mapBlockchainChart(it) }
}