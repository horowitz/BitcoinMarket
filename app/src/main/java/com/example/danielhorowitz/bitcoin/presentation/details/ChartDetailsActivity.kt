package com.example.danielhorowitz.bitcoin.presentation.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.danielhorowitz.bitcoin.Navigator
import com.example.danielhorowitz.bitcoin.R
import com.example.danielhorowitz.bitcoin.domain.model.Chart
import kotlinx.android.synthetic.main.activity_chart_details.*

private const val CHART_EXTRA = "chart_extra"

class ChartDetailsActivity : AppCompatActivity(R.layout.activity_chart_details) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        val chart = requireChartExtra()
        chartView.bind(chart)
    }

    companion object {
        fun newIntent(context: Context, chart: Chart) =
            Intent(context, ChartDetailsActivity::class.java)
                .apply { putExtra(CHART_EXTRA, chart) }
    }

    private fun AppCompatActivity.requireChartExtra(): Chart = requireNotNull(
        intent.getParcelableExtra(Navigator.CHART_EXTRA),
        { "Missing chart extra!!" }
    )
}
