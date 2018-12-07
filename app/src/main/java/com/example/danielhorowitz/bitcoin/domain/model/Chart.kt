package com.example.danielhorowitz.bitcoin.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Chart(
    val unit: String = "",
    val period: String = "",
    val values: List<Pair<Double, Double>> = listOf(),
    val name: String = "",
    val description: String = ""
) : Parcelable