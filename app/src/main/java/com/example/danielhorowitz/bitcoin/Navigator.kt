package com.example.danielhorowitz.bitcoin

import android.app.Activity
import android.content.Intent
import com.example.danielhorowitz.bitcoin.Navigator.Companion.CHART_EXTRA
import com.example.danielhorowitz.bitcoin.domain.model.Chart
import com.example.danielhorowitz.bitcoin.presentation.details.ChartDetailsActivity

interface Navigator {
    companion object {
        const val CHART_EXTRA = "chart_extra"
    }

    fun navigateToChartDetails(chart: Chart)
}

class NavigatorImpl(private val activity: Activity) : Navigator {

    override fun navigateToChartDetails(chart: Chart) {
        val intent = Intent(activity, ChartDetailsActivity::class.java)
        intent.putExtra(CHART_EXTRA, chart)
        activity.startActivity(intent)
    }

}