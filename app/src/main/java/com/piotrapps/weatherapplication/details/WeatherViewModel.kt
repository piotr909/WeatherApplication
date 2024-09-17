package com.piotrapps.weatherapplication.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piotrapps.domain.usecase.GetWeatherUseCase
import com.piotrapps.weatherapplication.details.model.WeatherUiState
import com.piotrapps.weatherapplication.details.model.mapper.toUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getWeatherUseCase: GetWeatherUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _weatherState = MutableStateFlow<WeatherUiState>(WeatherUiState.Loading)
    val weatherUiState = _weatherState.asStateFlow()

    private val countryName: String = savedStateHandle.get<String>("countryName") ?: ""
    private val countryCode: String = savedStateHandle.get<String>("countryCode") ?: ""

    init {
        getWeatherData()
    }

    fun getWeatherData() {
        viewModelScope.launch {
            getWeatherUseCase(countryName, countryCode)
                .catch {
                    _weatherState.value = WeatherUiState.Error
                }
                .collect {
                    _weatherState.value = it.toUi()
                }
        }
    }
}