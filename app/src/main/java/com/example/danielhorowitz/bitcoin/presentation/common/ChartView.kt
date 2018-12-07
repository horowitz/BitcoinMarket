package com.example.danielhorowitz.bitcoin.presentation.common

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.example.danielhorowitz.bitcoin.R
import com.example.danielhorowitz.bitcoin.domain.model.Chart


class ChartView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : RelativeLayout(context, attrs, defStyle) {

    init {
        LayoutInflater.from(context).inflate(R.layout.chart_view, this, true)
    }

    fun bind(chart: Chart) {

    }
}