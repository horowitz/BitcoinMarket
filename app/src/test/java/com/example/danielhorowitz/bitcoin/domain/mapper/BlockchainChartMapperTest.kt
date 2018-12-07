package com.example.danielhorowitz.bitcoin.domain.mapper

import com.example.danielhorowitz.bitcoin.data.model.BlockchainChartDTO
import com.example.danielhorowitz.bitcoin.domain.model.Chart
import com.example.danielhorowitz.bitcoin.fromJson
import com.example.danielhorowitz.bitcoin.getResource
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class BlockchainChartMapperTest {
    private val blockchainChartMapper = BlockchainChartMapper()

    private lateinit var chartDTO: BlockchainChartDTO

    @Before
    fun setUp() {
        chartDTO = getResource("chart.json").fromJson(BlockchainChartDTO::class.java)
    }

    @Test
    fun `should map correctly blockchain dtos`() {
        val expected = Chart(
            "USD",
            "day",
            listOf(Pair(1.0, 2.0), Pair(3.0, 4.0)),
            "Market Price (USD)",
            "Average USD market price across major bitcoin exchanges."
        )
        val actual = blockchainChartMapper.mapBlockchainChart(chartDTO)
        Assert.assertEquals(expected.description, actual.description)
        Assert.assertEquals(expected.unit, actual.unit)
        Assert.assertEquals(expected.period, actual.period)
        Assert.assertEquals(expected.name, actual.name)
        Assert.assertEquals(expected.values, actual.values)
    }
}