package com.piotrapps.data.remote

import com.piotrapps.data.remote.dto.CityDto
import com.piotrapps.data.remote.dto.WeatherDto
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("/geo/1.0/direct?lang=pl&limit=50")
    suspend fun searchCity(
        @Query("q") query: String,
        @Query("appid") appid: String
    ): List<CityDto>

    @GET("/data/2.5/weather?units=metric&lang=pl")
    suspend fun getWeather(
        @Query("q") cityName: String,
        @Query("appid") appid: String
    ): WeatherDto
}