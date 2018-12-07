package com.example.danielhorowitz.bitcoin.domain.mapper

import com.example.danielhorowitz.bitcoin.data.model.BlockchainChartDTO
import com.example.danielhorowitz.bitcoin.data.model.ValuesItem
import com.example.danielhorowitz.bitcoin.domain.model.Chart

interface ChartMapper {
    fun mapBlockchainChart(chartDTO: BlockchainChartDTO): Chart
}

class BlockchainChartMapper : ChartMapper {
    override fun mapBlockchainChart(chartDTO: BlockchainChartDTO): Chart {
        with(chartDTO) {
            return Chart(unit, period, mapValues(chartDTO.values), name, description)
        }
    }

    private fun mapValues(values: List<ValuesItem>?): List<Pair<Double, Double>> {
        requireNotNull(values)
        return values.map { Pair(it.x, it.y) }
    }
}