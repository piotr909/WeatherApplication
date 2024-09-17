package com.piotrapps.data.remote.dto

import com.google.gson.annotations.SerializedName

data class WeatherDto(
    @SerializedName("name") val name: String,
    @SerializedName("main") val mainWeatherDto: MainWeatherDto,
    @SerializedName("weather") val weatherDescriptionDto: List<WeatherDescriptionDto>,
    @SerializedName("sys") val weatherSysDto: WeatherSysDto,

    )

data class MainWeatherDto(
    @SerializedName("temp") val currentTemp: Float,
    @SerializedName("feels_like") val feelsTemp: Float,
    @SerializedName("pressure") val pressure: Int,
    @SerializedName("humidity") val humidity: Int,
)

data class WeatherDescriptionDto(
    @SerializedName("main") val condition: WeatherConditionDto,
    @SerializedName("description") val description: String,
)

data class WeatherSysDto(
    @SerializedName("id") val id: Int,
    @SerializedName("country") val countryCode: String,
)

enum class WeatherConditionDto {
    Clear, Rain, Snow, Clouds
}