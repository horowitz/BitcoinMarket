package com.example.danielhorowitz.bitcoin.domain.charts

import com.example.danielhorowitz.bitcoin.data.repository.ChartsRepository
import com.example.danielhorowitz.bitcoin.domain.mapper.ChartMapper
import com.example.danielhorowitz.bitcoin.domain.model.Chart
import io.reactivex.Single

interface ChartsInteractor {
    fun fetchChart(timeSpan: String, rollingAvg: String): Single<Chart>

}

class ChartsInteractorImpl(
    private val chartsRepository: ChartsRepository,
    private val chartMapper: ChartMapper
) : ChartsInteractor {
    override fun fetchChart(timeSpan: String, rollingAvg: String): Single<Chart> =
        chartsRepository.fetchChart(timeSpan, rollingAvg).map { chartMapper.mapBlockchainChart(it) }
}