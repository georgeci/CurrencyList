package com.georgeci.purevm.sample.domain.repository

import arrow.core.Either
import com.georgeci.purevm.sample.domain.entity.CurrencyExchangeRate
import com.georgeci.purevm.sample.domain.entity.CurrencyItem
import com.georgeci.purevm.sample.domain.entity.RemoteError

interface CurrencyRepository {
    suspend fun getCurrencyList(): Either<RemoteError, List<CurrencyItem>>
    suspend fun getCurrencyExchangeRates(code:String): Either<RemoteError, List<CurrencyExchangeRate>>
}