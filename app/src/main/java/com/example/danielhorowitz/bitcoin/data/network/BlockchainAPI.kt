package com.example.danielhorowitz.bitcoin.data.network

import com.example.danielhorowitz.bitcoin.data.model.BlockchainChartDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BlockchainAPI {
    @GET(BlockchainConfig.CHART_ENDPOINT)
    suspend fun fetchChart(
        @Path(BlockchainConfig.Path.CHART_NAME) name: String,
        @Query(BlockchainConfig.Params.FORMAT) format: String = BlockchainConfig.Params.Values.JSON_FORMAT
    ): BlockchainChartDTO
}
