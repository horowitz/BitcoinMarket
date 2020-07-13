package com.example.danielhorowitz.bitcoin.presentation.charts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.danielhorowitz.bitcoin.R
import com.example.danielhorowitz.bitcoin.domain.model.Chart
import kotlinx.android.synthetic.main.chart_item.view.*

class ChartsAdapter(
    private val itemClicked: (Chart) -> Unit
) : ListAdapter<Chart, ChartsAdapter.ItemViewholder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewholder {
        return ItemViewholder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.chart_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewholder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ItemViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Chart) = with(itemView) {
            chartView.bind(item, true)

            setOnClickListener { itemClicked(item) }
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<Chart>() {
    override fun areItemsTheSame(oldItem: Chart, newItem: Chart): Boolean =
        oldItem.name == newItem.name

    override fun areContentsTheSame(oldItem: Chart, newItem: Chart): Boolean = oldItem == newItem
}
