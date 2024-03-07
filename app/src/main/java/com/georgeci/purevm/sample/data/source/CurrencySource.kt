package com.georgeci.purevm.sample.data.source

import com.georgeci.purevm.sample.data.entity.RateResponse
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Path

typealias CurrResponse = HashMap<String, String>

interface CurrencySource {
    @GET("gh/fawazahmed0/currency-api@1/latest/currencies.json")
    suspend fun getCurrencyList(): Map<String, String>

    @GET("gh/fawazahmed0/currency-api@1/latest/currencies/{code}.min.json")
    suspend fun getExchangeRates(@Path("code") code: String): RateResponse
}