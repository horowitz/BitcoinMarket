package com.example.danielhorowitz.bitcoin.presentation.common

import com.example.danielhorowitz.bitcoin.R


interface BaseView {
    fun showError(throwable: Throwable, tag: String = "", message: Int = R.string.unexpected_error )
    fun showLoading()
    fun hideLoading()
}
