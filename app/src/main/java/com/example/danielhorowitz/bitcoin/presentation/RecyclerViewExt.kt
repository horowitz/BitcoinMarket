package com.example.danielhorowitz.bitcoin.presentation

import androidx.annotation.DimenRes
import androidx.recyclerview.widget.RecyclerView
import com.example.danielhorowitz.bitcoin.R
import com.example.danielhorowitz.bitcoin.presentation.common.EqualSpacingItemDecoration
import org.jetbrains.anko.dimen

internal fun RecyclerView.addEqualSpacingBetweenItems(@DimenRes spacing: Int = R.dimen.default_spacing) {
    addItemDecoration(
        EqualSpacingItemDecoration(
            dimen(spacing)
        )
    )
}
