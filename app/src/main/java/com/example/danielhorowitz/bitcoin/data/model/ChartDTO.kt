package com.example.danielhorowitz.bitcoin.data.model


import com.google.gson.annotations.SerializedName

data class ValuesItem(@SerializedName("x")
                      val x: Int = 0,
                      @SerializedName("y")
                      val y: Double = 0.0)


data class ChartDTO(@SerializedName("unit")
                    val unit: String = "",
                    @SerializedName("period")
                    val period: String = "",
                    @SerializedName("values")
                    val values: List<ValuesItem>?,
                    @SerializedName("name")
                    val name: String = "",
                    @SerializedName("description")
                    val description: String = "",
                    @SerializedName("status")
                    val status: String = "")


