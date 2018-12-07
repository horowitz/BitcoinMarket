package com.example.danielhorowitz.bitcoin.presentation.charts

import android.app.Activity
import android.os.Bundle
import dagger.android.AndroidInjection

class ChartsActivity : Activity(), ChartsContract.View {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
    }
}