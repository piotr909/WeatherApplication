package com.piotrapps.weatherapplication.details.model

sealed class WeatherUiState {

    data class Weather(
        val cityName: String,
        val temp: Int,
        val feelTemp: Int,
        val humidity: Int,
        val pressure: Int,
        val description: String,
        val condition: WeatherConditionUi
    ) : WeatherUiState()

    data object Error : WeatherUiState()

    data object Loading : WeatherUiState()
}

enum class WeatherConditionUi {
    Clear, Rain, Snow, Clouds, Unspecified
}
