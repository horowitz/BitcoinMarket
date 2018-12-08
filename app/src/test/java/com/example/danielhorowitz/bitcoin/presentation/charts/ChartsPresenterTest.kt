package com.example.danielhorowitz.bitcoin.presentation.charts

import com.example.danielhorowitz.bitcoin.Navigator
import com.example.danielhorowitz.bitcoin.domain.charts.ChartsInteractor
import com.example.danielhorowitz.bitcoin.domain.model.Chart
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class ChartsPresenterTest {

    @Mock
    lateinit var view: ChartsContract.View
    @Mock
    lateinit var interactor: ChartsInteractor
    @Mock
    lateinit var navigator: Navigator

    private val presenter by lazy {
        ChartsPresenter(view, interactor, navigator, Schedulers.trampoline(), Schedulers.trampoline())
    }

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `should navigate to details when chart clicked`() {
        val chart = Chart()
        presenter.onChartClicked(chart)

        verify(navigator).navigateToChartDetails(chart)
    }

    @Test
    fun `should show charts given popular charts fetched successfully `() {
        val charts = listOf(Chart())
        givenChartsFetched(charts)

        presenter.fetchPopularCharts()

        verify(view).showCharts(charts)
    }

    @Test
    fun `should show loading when fetching charts`() {
        givenChartsFetched(listOf(Chart()))

        presenter.fetchPopularCharts()

        verify(view).showLoading()
    }

    @Test
    fun `should hide loading when charts fetched`() {
        givenChartsFetched(listOf(Chart()))

        presenter.fetchPopularCharts()

        verify(view).hideLoading()
    }

    @Test
    fun `should show error given charts fetch fails`() {
        val exception = Exception()
        givenChartsFetchFails(exception)

        presenter.fetchPopularCharts()

        verify(view).showError(exception)
    }

    private fun givenChartsFetchFails(exception: Exception) {
        whenever(interactor.fetchPopularCharts()).thenReturn(Single.error(exception))
    }

    private fun givenChartsFetched(charts: List<Chart>) {
        whenever(interactor.fetchPopularCharts()).thenReturn(Single.just(charts))
    }
}