package com.piotrapps.weatherapplication.list.model

sealed class CitiesListUiState {
    data class History(
        val cities: List<City>
    ) : CitiesListUiState()

    data class QueryResult(
        val cities: List<City>
    ) : CitiesListUiState()

    data object Error: CitiesListUiState()
}

data class City(val name: String, val countryCode: String)