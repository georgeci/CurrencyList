package com.georgeci.purevm.sample.presentation.feature.list

import com.georgeci.purevm.sample.domain.entity.CurrencyExchangeRate
import com.georgeci.purevm.sample.domain.entity.CurrencyItem

sealed class ListState {
    data object Loading : ListState()
    data class Loaded(val items: List<CurrencyItem>) : ListState()
    data object Error : ListState()
}