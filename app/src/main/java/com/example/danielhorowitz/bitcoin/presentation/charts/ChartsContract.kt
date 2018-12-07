package com.example.danielhorowitz.bitcoin.presentation.charts

import com.example.danielhorowitz.bitcoin.domain.model.Chart
import com.example.danielhorowitz.bitcoin.presentation.common.BasePresenter
import com.example.danielhorowitz.bitcoin.presentation.common.BaseView

interface ChartsContract{
    interface View: BaseView{
        fun showCharts(charts: List<Chart>)

    }
    interface Presenter: BasePresenter{
        fun fetchPopularCharts()
        fun onChartClicked(chart: Chart)
    }
}