package com.example.danielhorowitz.bitcoin.data.repository

import com.example.danielhorowitz.bitcoin.data.model.ChartDTO
import com.example.danielhorowitz.bitcoin.data.network.BlockchainAPI
import com.example.danielhorowitz.bitcoin.data.network.BlockchainConfig
import io.reactivex.Single

interface ChartsRepository {
    fun fetchChart(timeSpan: String, rollingAvg: String): Single<ChartDTO>
}

class BlockchainChartRepository(private val blockchainAPI: BlockchainAPI) : ChartsRepository {

    override fun fetchChart(timeSpan: String, rollingAvg: String): Single<ChartDTO> {
        return blockchainAPI.fetchChart(
            BlockchainConfig.Params.Values.JSON_FORMAT,
            rollingAvg,
            timeSpan
        )
    }

}