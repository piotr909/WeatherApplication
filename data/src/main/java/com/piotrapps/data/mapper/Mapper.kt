package com.piotrapps.data.mapper

import com.piotrapps.data.db.entity.CityEntity
import com.piotrapps.data.remote.dto.CityDto
import com.piotrapps.data.remote.dto.WeatherConditionDto
import com.piotrapps.data.remote.dto.WeatherDto
import com.piotrapps.domain.model.City
import com.piotrapps.domain.model.Weather
import com.piotrapps.domain.model.WeatherCondition

fun CityEntity.toDomain(): City {
    return City(
        name = this.name,
        countryCode = this.countryCode
    )
}

fun CityDto.toDomainItem(): City {
    return City(
        name = this.name,
        countryCode = this.country
    )
}

fun WeatherDto.toDomain(): Weather {
    return Weather(
        name = this.name,
        currentTemp = this.mainWeatherDto.currentTemp.toInt(),
        feelsTemp = this.mainWeatherDto.feelsTemp.toInt(),
        description = this.weatherDescriptionDto.firstOrNull()?.description ?: "",
        pressure = this.mainWeatherDto.pressure,
        humidity = this.mainWeatherDto.humidity,
        weatherCondition = this.weatherDescriptionDto.firstOrNull()?.condition?.toDomain()
            ?: WeatherCondition.Unspecified
    )
}

fun WeatherConditionDto.toDomain(): WeatherCondition {
    return when (this) {
        WeatherConditionDto.Clear -> WeatherCondition.Clear
        WeatherConditionDto.Rain -> WeatherCondition.Rain
        WeatherConditionDto.Snow -> WeatherCondition.Snow
        WeatherConditionDto.Clouds -> WeatherCondition.Clouds
    }
}