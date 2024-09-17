package com.piotrapps.weatherapplication.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piotrapps.domain.usecase.GetCityHistoryUseCase
import com.piotrapps.domain.usecase.SearchCityUseCase
import com.piotrapps.weatherapplication.list.model.CitiesListUiState
import com.piotrapps.weatherapplication.list.model.mapper.toUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CitiesViewModel @Inject constructor(
    private val getCityHistoryUseCase: GetCityHistoryUseCase,
    private val searchCityUseCase: SearchCityUseCase
) : ViewModel() {

    private val _citiesListUiState = MutableStateFlow<CitiesListUiState?>(null)
    val citiesListUiState = _citiesListUiState.asStateFlow()

    init {
        getHistory()
    }

    private fun getHistory() {
        viewModelScope.launch {
            getCityHistoryUseCase()
                .catch {
                    _citiesListUiState.update {
                        CitiesListUiState.Error
                    }
                    _citiesListUiState.value = CitiesListUiState.Error
                }
                .collect { cities ->
                    _citiesListUiState.update {
                        CitiesListUiState.History(cities.map {
                            it.toUi()
                        })
                    }
                }
        }
    }

    fun searchCity(query: String) {
        viewModelScope.launch {
            searchCityUseCase(query)
                .catch {
                    _citiesListUiState.update {
                        CitiesListUiState.Error
                    }
                }
                .collect { cities ->
                    _citiesListUiState.update {
                        CitiesListUiState.QueryResult(cities.map {
                            it.toUi()
                        })
                    }
                }
        }
    }

    fun clearSearch() {
        getHistory()
    }
}