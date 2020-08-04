package com.example.danielhorowitz.bitcoin.presentation.charts

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.danielhorowitz.bitcoin.SingleLiveEvent
import com.example.danielhorowitz.bitcoin.domain.charts.FetchPopularCharts
import com.example.danielhorowitz.bitcoin.domain.model.Chart
import com.example.danielhorowitz.bitcoin.presentation.model.ChartsActions
import com.example.danielhorowitz.bitcoin.presentation.model.ChartsActions.*
import com.example.danielhorowitz.bitcoin.presentation.model.ChartsEvent
import com.example.danielhorowitz.bitcoin.presentation.model.ChartsState
import com.example.danielhorowitz.bitcoin.presentation.model.ChartsState.*
import kotlinx.coroutines.launch

internal class ChartsViewModel @ViewModelInject constructor(
    private val fetchPopularCharts: FetchPopularCharts
) : ViewModel() {
    private val mutableViewState = MutableLiveData<ChartsState>()
    val state: LiveData<ChartsState> = mutableViewState

    private val mutableViewEvent = SingleLiveEvent<ChartsEvent>()
    val event: LiveData<ChartsEvent> = mutableViewEvent

    fun handle(action: ChartsActions) = when (action) {
        Start, Refresh -> loadContent()
        is ChartClicked -> onChartClicked(action.item)
    }

    private fun onChartClicked(chart: Chart) {
        mutableViewEvent.value = ChartsEvent.NavigateToDetails(chart)
    }

    private fun loadContent() {
        mutableViewState.value = Loading

        viewModelScope.launch {
            runCatching { fetchPopularCharts() }
                .onSuccess { mutableViewState.value = Content(it) }
                .onFailure { mutableViewState.value = Error }
        }
    }
}
