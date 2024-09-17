package com.piotrapps.data.di

import com.piotrapps.data.db.dao.CitiesDao
import com.piotrapps.data.remote.WeatherApi
import com.piotrapps.data.repository.WeatherRepositoryImpl
import com.piotrapps.domain.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideWeatherRepository(weatherApi: WeatherApi, citiesDao: CitiesDao): WeatherRepository {
        return WeatherRepositoryImpl(weatherApi, citiesDao)
    }
}