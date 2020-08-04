package com.example.danielhorowitz.bitcoin

import com.example.danielhorowitz.bitcoin.data.model.BlockchainChartDTO
import com.example.danielhorowitz.bitcoin.data.model.ValuesItem
import com.example.danielhorowitz.bitcoin.domain.model.Chart

fun blockchainChartDTO(
    status: String = "ok",
    name: String = "Market Price (USD)",
    unit: String = "USD",
    period: String = "day",
    description: String = "Average USD market price across major bitcoin exchanges.",
    values: List<ValuesItem> = listOf(
        ValuesItem(x = 1.toDouble(), y = 2.toDouble()),
        ValuesItem(x = 3.toDouble(), y = 4.toDouble())
    )
) = BlockchainChartDTO(unit, period, values, name, description, status)


fun chart(
    unit: String = "USD",
    period: String = "day",
    name: String = "Market Price (USD)",
    description: String = "Average USD market price across major bitcoin exchanges.",
    values: List<Pair<Double, Double>> = listOf(
        1.toDouble() to 2.toDouble(),
        3.toDouble() to 4.toDouble()
    )
) = Chart(unit, period, values, name, description)
