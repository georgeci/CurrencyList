package com.georgeci.purevm.sample.app.di

import com.georgeci.purevm.sample.data.source.CurrencySource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import de.jensklingenberg.ktorfit.Ktorfit
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @OptIn(ExperimentalSerializationApi::class)
    @Provides
    fun json() = Json {
        isLenient = true
        ignoreUnknownKeys = true
        explicitNulls = false
    }

    @Provides
    fun provideHttpClient(json: Json): HttpClient {
        return HttpClient(CIO) {
            install(ContentNegotiation) {
                json(json)
            }
        }
    }

    @Provides
    fun provideCurrencySource(apiClient: HttpClient): CurrencySource {
        val ktorfit = Ktorfit.Builder()
            .httpClient(apiClient)
            .baseUrl("https://cdn.jsdelivr.net/")
            .build()
        return ktorfit.create()
    }
}