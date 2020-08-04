package com.example.danielhorowitz.bitcoin.presentation.charts

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.danielhorowitz.bitcoin.chart
import com.example.danielhorowitz.bitcoin.domain.charts.FetchPopularCharts
import com.example.danielhorowitz.bitcoin.presentation.model.ChartsActions.Start
import com.example.danielhorowitz.bitcoin.presentation.model.ChartsState
import com.example.danielhorowitz.bitcoin.test
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ChartsViewModelTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private val fetchPopularCharts = mock<FetchPopularCharts>()
    private val sut = ChartsViewModel(fetchPopularCharts)

    @Test
    fun `should load content when view model starts given charts fetched ok`() {
        runBlockingTest {
            givenChartsFetched()

            val observer = sut.state.test()

            sut.handle(Start)

            val expected = listOf(chart())

            observer.assertValues(
                ChartsState.Loading,
                ChartsState.Content(expected)
            )
        }
    }

    private suspend fun givenChartsFetched() {
        whenever(fetchPopularCharts()).thenReturn(listOf(chart()))
    }

}
