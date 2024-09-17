package com.piotrapps.domain.usecase

import com.piotrapps.domain.model.City
import com.piotrapps.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchCityUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {

    suspend operator fun invoke(query: String): Flow<List<City>> {
        return weatherRepository.searchCity(query)
    }
}