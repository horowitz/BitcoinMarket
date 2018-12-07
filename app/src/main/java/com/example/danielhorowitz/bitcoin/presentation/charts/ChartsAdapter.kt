package com.example.danielhorowitz.bitcoin.presentation.charts

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.danielhorowitz.bitcoin.R
import com.example.danielhorowitz.bitcoin.domain.model.Chart
import com.example.danielhorowitz.bitcoin.presentation.common.ChartView
import org.jetbrains.anko.sdk27.coroutines.onClick

class ChartsAdapter(
    private val items: List<Chart>,
    private val itemClicked: (Chart) -> Unit
) : RecyclerView.Adapter<ChartsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.chart_item, parent, false))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position], itemClicked)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val chartView = itemView.findViewById<ChartView>(R.id.chartView)

        fun bind(chart: Chart, itemClicked: (Chart) -> Unit) {
            chartView.bind(chart,true)
            itemView.onClick { itemClicked(chart) }
        }
    }
}
