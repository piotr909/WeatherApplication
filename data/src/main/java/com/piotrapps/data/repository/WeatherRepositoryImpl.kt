package com.piotrapps.data.repository

import com.piotrapps.data.db.dao.CitiesDao
import com.piotrapps.data.db.entity.CityEntity
import com.piotrapps.data.mapper.toDomain
import com.piotrapps.data.mapper.toDomainItem
import com.piotrapps.data.remote.Constants
import com.piotrapps.data.remote.WeatherApi
import com.piotrapps.domain.model.City
import com.piotrapps.domain.model.Weather
import com.piotrapps.domain.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi,
    private val citiesDao: CitiesDao
) : WeatherRepository {

    override suspend fun searchCity(query: String): Flow<List<City>> {
        return flow {
            emit(
                weatherApi.searchCity(
                    query,
                    Constants.API_KEY
                )
            )
        }
            .map { list ->
                list.map {
                    it.toDomainItem()
                }
            }
            .flowOn(Dispatchers.IO)
    }

    override suspend fun getCitiesHistory(): Flow<List<City>> {
        return flow {
            emit(
                citiesDao.getCities()
            )
        }
            .map { list ->
                list.map {
                    it.toDomain()
                }
            }
            .flowOn(Dispatchers.IO)
    }

    override suspend fun getWeather(query: String): Flow<Weather> {
        return flow {
            emit(
                weatherApi.getWeather(
                    query,
                    Constants.API_KEY
                )
            )
        }
            .onEach {
                citiesDao.addCity(
                    CityEntity(
                        it.weatherSysDto.id,
                        it.name,
                        it.weatherSysDto.countryCode
                    )
                )
            }
            .map {
                it.toDomain()
            }
            .flowOn(Dispatchers.IO)
    }
}