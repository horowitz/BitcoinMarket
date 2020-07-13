package com.example.danielhorowitz.bitcoin.domain.mapper

import com.example.danielhorowitz.bitcoin.data.model.BlockchainChartDTO
import com.example.danielhorowitz.bitcoin.data.model.ValuesItem
import com.example.danielhorowitz.bitcoin.domain.model.Chart

internal fun BlockchainChartDTO.toBlockChainChart() = Chart(
    unit = unit,
    period = period,
    values = requireNotNull(values?.map { it.x to it.y }),
    name = name,
    description = description
)
