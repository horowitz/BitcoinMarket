package com.example.danielhorowitz.bitcoin.presentation.model

import com.example.danielhorowitz.bitcoin.domain.model.Chart

internal sealed class ChartsEvent {
    data class NavigateToDetails(val chart: Chart): ChartsEvent()
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
