package com.example.danielhorowitz.bitcoin.data.network

import com.example.danielhorowitz.bitcoin.data.model.BlockchainChartDTO
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface BlockchainAPI {
    @GET(BlockchainConfig.CHART_ENDPOINT)
    fun fetchChart(
        @Query(BlockchainConfig.Params.FORMAT) format: String,
        @Query(BlockchainConfig.Params.ROLLING_AVERAGE) rollingAverage: String,
        @Query(BlockchainConfig.Params.TIMESPAN) timeSpan: String
    ): Single<BlockchainChartDTO>
}