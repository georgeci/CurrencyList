package com.georgeci.purevm.sample.presentation.feature.details

import com.georgeci.purevm.sample.domain.entity.CurrencyExchangeRate

sealed class DetailsState {
    abstract val code: String
    abstract val name: String

    data class Loading(
        override val code: String,
        override val name: String,
    ) : DetailsState()

    data class Loaded(
        override val code: String,
        override val name: String,
        val items: List<CurrencyExchangeRate>,
    ) :
        DetailsState()

    data class Error(
        override val code: String,
        override val name: String,
    ) : DetailsState()
}