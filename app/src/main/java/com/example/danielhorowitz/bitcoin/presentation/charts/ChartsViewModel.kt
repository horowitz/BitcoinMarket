package com.example.danielhorowitz.bitcoin.presentation.charts

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    fun handle(action: ChartsActions) = when (action) {
        Start -> handleStart()
        is ChartClicked -> handleChartClicked(action.item)
    }

    private fun handleChartClicked(chart: Chart) {

    }

    private fun handleStart() {
        mutableViewState.value = Loading

        viewModelScope.launch {
            runCatching { fetchPopularCharts() }
                .onSuccess { mutableViewState.value = Content(it) }
                .onFailure { mutableViewState.value = Error(it) }
        }
    }
}


internal sealed class ChartsActions {
    object Start : ChartsActions()
    data class ChartClicked(val item: Chart) : ChartsActions()
}

internal sealed class ChartsState {
    object Loading : ChartsState()
    data class Content(val charts: List<Chart>) : ChartsState()
    data class Error(val error: Throwable) : ChartsState()
}
