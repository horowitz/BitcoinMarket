package com.example.danielhorowitz.bitcoin.presentation.common

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.example.danielhorowitz.bitcoin.R
import com.example.danielhorowitz.bitcoin.domain.model.Chart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import kotlinx.android.synthetic.main.chart_view.view.*
import java.text.SimpleDateFormat
import java.util.*


class ChartView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : RelativeLayout(context, attrs, defStyle) {

    init {
        LayoutInflater.from(context).inflate(R.layout.chart_view, this, true)
    }

    fun bind(chart: Chart, isMinimal: Boolean = false) {
        tvTitle.text = chart.name
        val entries = chart.values.map { Entry(it.first.toFloat(), it.second.toFloat()) }
        val dataSet = LineDataSet(entries, chart.name)
        dataSet.setDrawCircles(false)
        lineChart.data = LineData(dataSet)
        if (isMinimal) setupChartToMinimal()
        formatXAxis()
        setDescription(chart)
    }

    private fun formatXAxis() {
        lineChart.xAxis.setValueFormatter { value, _ -> formatValueToDate(value * 1000) }
    }

    private fun formatValueToDate(value: Float): String {
        val date = Date(value.toLong())
        val simpleDateFormatter = SimpleDateFormat("dd.MMM", Locale.getDefault())
        return simpleDateFormatter.format(date)
    }

    private fun setupChartToMinimal() {
        lineChart.isFocusableInTouchMode = false
        lineChart.setTouchEnabled(false)
        lineChart.isDoubleTapToZoomEnabled = false
        lineChart.isHighlightPerTapEnabled = false
        lineChart.setPinchZoom(false)
        lineChart.setScaleEnabled(false)
    }

    private fun setDescription(chart: Chart) {
        val description = Description()
        description.text = chart.description
        lineChart.description = description
    }
}