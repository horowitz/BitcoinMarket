package com.example.danielhorowitz.bitcoin.domain.charts

import com.example.danielhorowitz.bitcoin.blockchainChartDTO
import com.example.danielhorowitz.bitcoin.chart
import com.example.danielhorowitz.bitcoin.data.model.BlockchainChartDTO
import com.example.danielhorowitz.bitcoin.data.repository.BlockchainChartNetworkDataSource
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Test

@ExperimentalCoroutinesApi
internal class FetchPopularChartsTest {
    private val dataSource = mock<BlockchainChartNetworkDataSource>()
    private val sut = FetchPopularCharts(dataSource)

    @Test
    fun `should return charts given data source fetch success`() {
        runBlockingTest {
            givenDataSourceFetchSuccess(blockchainChartDTO())

            val actual = sut.invoke()

            val expected = listOf(chart(),chart(),chart(),chart())

            assertEquals(expected, actual)
        }
    }


    private suspend fun givenDataSourceFetchSuccess(dto: BlockchainChartDTO) {
        whenever(dataSource.fetchChart(any())).thenReturn(dto)
    }
}
