package com.georgeci.purevm.sample.data.repository

import arrow.core.Either
import arrow.core.left
import arrow.core.raise.catch
import arrow.core.right
import com.georgeci.purevm.sample.data.source.CurrencySource
import com.georgeci.purevm.sample.domain.entity.CurrencyExchangeRate
import com.georgeci.purevm.sample.domain.entity.CurrencyItem
import com.georgeci.purevm.sample.domain.entity.RemoteError
import com.georgeci.purevm.sample.domain.repository.CurrencyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DefaultCurrencyRepository @Inject constructor(
    private val source: CurrencySource,
) : CurrencyRepository {
    override suspend fun getCurrencyList(): Either<RemoteError, List<CurrencyItem>> =
        withContext(Dispatchers.IO) {
            catch({
                source.getCurrencyList()
                    .entries
                    .mapNotNull { (id, name) ->
                        if (name.isNotEmpty()) {
                            CurrencyItem(id, name)
                        } else {
                            null
                        }
                    }
                    .right()
            }) {
                RemoteError.Unknown(it.message).left()
            }
        }

    override suspend fun getCurrencyExchangeRates(code: String): Either<RemoteError, List<CurrencyExchangeRate>> =
        withContext(Dispatchers.IO) {
            catch({
                source.getExchangeRates(code).items
                    .entries
                    .mapNotNull { (id, name) ->
                        if (name.isNotEmpty()) {
                            CurrencyExchangeRate(id, name)
                        } else {
                            null
                        }

                    }
                    .right()
            }) {
                RemoteError.Unknown(it.message).left()
            }
        }
}