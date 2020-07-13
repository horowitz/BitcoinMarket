package com.example.danielhorowitz.bitcoin.data.repository

import com.example.danielhorowitz.bitcoin.data.model.BlockchainChartDTO
import com.example.danielhorowitz.bitcoin.data.network.BlockchainAPI
import javax.inject.Inject

class BlockchainChartNetworkDataSource @Inject constructor(private val blockchainAPI: BlockchainAPI) {
    suspend fun fetchChart(name: String): BlockchainChartDTO = blockchainAPI.fetchChart(name)
}
