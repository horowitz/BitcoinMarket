package com.example.danielhorowitz.bitcoin.presentation.charts

import androidx.annotation.IdRes
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.danielhorowitz.bitcoin.SingleLiveEvent
import com.example.danielhorowitz.bitcoin.domain.charts.FetchPopularCharts
import com.example.danielhorowitz.bitcoin.domain.model.Chart
import com.example.danielhorowitz.bitcoin.presentation.charts.ChartsActions.*
import com.example.danielhorowitz.bitcoin.presentation.charts.ChartsState.*
import kotlinx.coroutines.launch

internal class ChartsViewModel @ViewModelInject constructor(
    private val fetchPopularCharts: FetchPopularCharts
) : ViewModel() {
    private val mutableViewState = MutableLiveData<ChartsState>()
    val state: LiveData<ChartsState> get() = mutableViewState

    private val mutableViewEvent = SingleLiveEvent<ChartsEvent>()
    val event: LiveData<ChartsEvent> get() = mutableViewEvent

    fun handle(action: ChartsActions) = when (action) {
        Start, Refresh -> loadContent()
        is ChartClicked -> handleChartClicked(action.item)
    }

    private fun handleChartClicked(chart: Chart) {

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

internal sealed class ChartsEvent {
    data class Navigate(@IdRes val destination: Int): ChartsEvent()
}

internal sealed class ChartsActions {
    object Start : ChartsActions()
    object Refresh : ChartsActions()
    data class ChartClicked(val item: Chart) : ChartsActions()
}

internal sealed class ChartsState {
    object Loading : ChartsState()
    object Error : ChartsState()
    data class Content(val charts: List<Chart>) : ChartsState()
}
