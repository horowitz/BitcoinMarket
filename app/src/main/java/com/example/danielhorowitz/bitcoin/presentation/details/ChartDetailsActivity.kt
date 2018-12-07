package com.example.danielhorowitz.bitcoin.presentation.details

import android.app.Activity
import android.os.Bundle
import android.view.WindowManager
import com.example.danielhorowitz.bitcoin.Navigator
import com.example.danielhorowitz.bitcoin.R
import com.example.danielhorowitz.bitcoin.domain.model.Chart
import kotlinx.android.synthetic.main.activity_chart_details.*

class ChartDetailsActivity: Activity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        setContentView(R.layout.activity_chart_details)
        val chart = intent.getParcelableExtra<Chart>(Navigator.CHART_EXTRA)

        chartView.bind(chart)
    }
}