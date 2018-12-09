package com.example.danielhorowitz.bitcoin.domain.charts

import com.example.danielhorowitz.bitcoin.data.model.BlockchainChartDTO
import com.example.danielhorowitz.bitcoin.data.repository.ChartsRepository
import com.example.danielhorowitz.bitcoin.domain.mapper.ChartMapper
import com.example.danielhorowitz.bitcoin.domain.model.Chart
import com.example.danielhorowitz.bitcoin.fromJson
import com.example.danielhorowitz.bitcoin.getResource
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.anyOrNull
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class ChartsInteractorImplTest {

    @Mock
    lateinit var repository: ChartsRepository
    @Mock
    lateinit var chartMapper: ChartMapper

    private lateinit var chartDTO: BlockchainChartDTO

    private val interactor by lazy { ChartsInteractorImpl(repository, chartMapper) }

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        chartDTO = getResource("chart.json").fromJson(BlockchainChartDTO::class.java)
    }

    @Test
    fun `should return chart data given chart retrieve successfully`() {
        givenChartMappedCorrectly()
        givenChartRetrieved()

        interactor.fetchPopularCharts()
            .test()
            .assertNoErrors()
            .assertComplete()
    }

    private fun givenChartRetrieved() {
        whenever(repository.fetchChart(any(), anyOrNull(), anyOrNull())).thenReturn(Single.just(chartDTO))
    }

    private fun givenChartMappedCorrectly() {
        whenever(chartMapper.mapBlockchainChart(any())).thenReturn(Chart())
    }
}