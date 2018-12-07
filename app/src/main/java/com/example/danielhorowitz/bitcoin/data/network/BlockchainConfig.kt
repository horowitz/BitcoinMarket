package com.example.danielhorowitz.bitcoin.data.network

import com.example.danielhorowitz.bitcoin.data.network.BlockchainConfig.Path.CHART_NAME

class BlockchainConfig {

    companion object {
        const val BASE_URL = "https://api.blockchain.info"
        const val CHART_ENDPOINT = "/charts/{$CHART_NAME}"
    }

    object Params {
        const val TIMESPAN = "timespan"
        const val ROLLING_AVERAGE = "rollingAverage"
        const val FORMAT = "format"

        object Values {
            const val WEEK = "weeks"
            const val HOURS = "hours"
            const val JSON_FORMAT = "json"
        }
    }

    object Path {
        const val CHART_NAME = "name"
    }

    object Charts {
        const val TRANSACTION_PER_SECOND = "transactions-per-second"
        const val MARKET_PRICE = "market-price"
        const val AVG_BLOCK_SIZE = "avg-block-size"
        const val MEMPOOL_SIZE = "mempool-size"
    }
}