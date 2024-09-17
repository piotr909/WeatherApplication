package com.piotrapps.domain.model

data class Weather(
    val name: String,
    val currentTemp: Int,
    val feelsTemp: Int,
    val description: String,
    val pressure: Int,
    val humidity: Int,
    val weatherCondition: WeatherCondition
)

enum class WeatherCondition {
    Clear, Rain, Snow, Clouds, Unspecified
}
