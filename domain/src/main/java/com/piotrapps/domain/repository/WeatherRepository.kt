package com.piotrapps.domain.repository

import com.piotrapps.domain.model.City
import com.piotrapps.domain.model.Weather
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {

    suspend fun searchCity(query: String): Flow<List<City>>

    suspend fun getCitiesHistory(): Flow<List<City>>

    suspend fun getWeather(query: String): Flow<Weather>
}