package com.example.danielhorowitz.bitcoin.data.repository

import com.example.danielhorowitz.bitcoin.data.model.BlockchainChartDTO
import com.example.danielhorowitz.bitcoin.data.network.BlockchainAPI
import com.example.danielhorowitz.bitcoin.data.network.BlockchainConfig
import io.reactivex.Single

interface ChartsRepository {
    fun fetchChart(name: String, timeSpan: String? = null, rollingAvg: String? = null): Single<BlockchainChartDTO>
}

class BlockchainChartRepository(private val blockchainAPI: BlockchainAPI) : ChartsRepository {

    override fun fetchChart(name: String, timeSpan: String?, rollingAvg: String?): Single<BlockchainChartDTO> {
        return blockchainAPI.fetchChart(
            name,
            BlockchainConfig.Params.Values.JSON_FORMAT
        )
    }

}