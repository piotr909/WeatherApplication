package com.piotrapps.weatherapplication.details.model.mapper

import com.piotrapps.domain.model.Weather
import com.piotrapps.domain.model.WeatherCondition
import com.piotrapps.weatherapplication.details.model.WeatherConditionUi
import com.piotrapps.weatherapplication.details.model.WeatherUiState

fun Weather.toUi() = WeatherUiState.Weather(
    cityName = this.name,
    temp = this.currentTemp,
    feelTemp = this.feelsTemp,
    humidity = this.humidity,
    pressure = this.pressure,
    description = this.description,
    condition = this.weatherCondition.toUi()
)

private fun WeatherCondition.toUi(): WeatherConditionUi {
    return when (this) {
        WeatherCondition.Clear -> WeatherConditionUi.Clear
        WeatherCondition.Rain -> WeatherConditionUi.Rain
        WeatherCondition.Snow -> WeatherConditionUi.Snow
        WeatherCondition.Clouds -> WeatherConditionUi.Clouds
        WeatherCondition.Unspecified -> WeatherConditionUi.Unspecified
    }
}
