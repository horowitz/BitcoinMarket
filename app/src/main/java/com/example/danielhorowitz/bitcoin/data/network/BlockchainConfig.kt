package com.example.danielhorowitz.bitcoin.data.network

import com.example.danielhorowitz.bitcoin.data.network.BlockchainConfig.Path.CHART_NAME

object BlockchainConfig {
    const val BASE_URL = "https://api.blockchain.info"
    const val CHART_ENDPOINT = "/charts/{$CHART_NAME}"

    object Params {
        const val FORMAT = "format"

        object Values {
            const val JSON_FORMAT = "json"
        }
    }

    object Path {
        const val CHART_NAME = "name"
    }

    enum class PopularCharts(val id: String) {
        TRANSACTION_PER_SECOND("transactions-per-second"),
        MARKET_PRICE("market-price"),
        AVG_BLOCK_SIZE("avg-block-size"),
        MEMPOOL_SIZE("mempool-size")
    }
}
