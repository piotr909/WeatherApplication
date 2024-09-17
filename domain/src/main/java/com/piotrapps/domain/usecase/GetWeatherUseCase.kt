package com.piotrapps.domain.usecase

import com.piotrapps.domain.model.Weather
import com.piotrapps.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetWeatherUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {

    suspend operator fun invoke(cityName: String, countryCode: String?): Flow<Weather> {
        return weatherRepository.getWeather("$cityName${countryCode?.run { ",$this" } ?: ""}")
    }
}