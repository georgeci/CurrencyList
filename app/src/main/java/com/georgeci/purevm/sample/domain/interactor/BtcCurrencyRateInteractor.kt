package com.georgeci.purevm.sample.domain.interactor

import com.georgeci.purevm.sample.domain.entity.CurrencyExchangeRate
import com.georgeci.purevm.sample.domain.repository.CurrencyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BtcCurrencyRateInteractor @Inject constructor(
    private val currencyRepository: CurrencyRepository,
) {
    fun observeBtcCurrencyRates(): Flow<List<CurrencyExchangeRate>> {
        return flow {

        }
    }
}