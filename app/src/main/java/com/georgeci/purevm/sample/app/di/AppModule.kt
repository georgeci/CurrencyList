package com.georgeci.purevm.sample.app.di

import com.georgeci.purevm.sample.data.repository.DefaultCurrencyRepository
import com.georgeci.purevm.sample.data.source.CurrencySource
import com.georgeci.purevm.sample.domain.repository.CurrencyRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    abstract fun provideCurrencyRepository(instance: DefaultCurrencyRepository): CurrencyRepository


}