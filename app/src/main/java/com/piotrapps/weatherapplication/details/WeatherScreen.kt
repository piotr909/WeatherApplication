package com.piotrapps.weatherapplication.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.piotrapps.weatherapplication.R
import com.piotrapps.weatherapplication.details.model.WeatherConditionUi
import com.piotrapps.weatherapplication.details.model.WeatherUiState
import com.piotrapps.weatherapplication.ui.theme.ClearWeatherBackground
import com.piotrapps.weatherapplication.ui.theme.ColdTemp
import com.piotrapps.weatherapplication.ui.theme.HotTemp
import com.piotrapps.weatherapplication.ui.theme.NormalTemp
import com.piotrapps.weatherapplication.ui.theme.RainyWeatherBackground

@Composable
fun WeatherScreen(
    countryName: String?,
    countryCode: String?,
    viewModel: WeatherViewModel = hiltViewModel()
) {
    val weatherUiState by viewModel.weatherUiState.collectAsState()

    when (val state = weatherUiState) {
        is WeatherUiState.Error -> {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 40.dp),
                    text = "Coś poszło nie tak, spróbuj ponownie",
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    color = Color.Black
                )

                Button(
                    modifier = Modifier
                        .align(CenterHorizontally)
                        .padding(top = 20.dp),
                    onClick = {
                        viewModel.getWeatherData()
                    }
                ) {
                    Text(
                        text = "Spróbuj ponownie"
                    )
                }
            }
        }

        is WeatherUiState.Weather -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = getBackgroundColorForCondition(state.condition))
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Icon(
                        imageVector = ImageVector.vectorResource(
                            id = getIconForCondition(
                                state.condition
                            )
                        ),
                        contentDescription = "weather condition icon",
                        tint = Color.White,
                        modifier = Modifier
                            .padding(top = 48.dp)
                            .size(80.dp)
                            .align(CenterHorizontally)
                    )

                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 32.dp),
                        text = "${state.temp}°C",
                        fontSize = 40.sp,
                        textAlign = TextAlign.Center,
                        color = getTempColor(state.temp)
                    )

                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 48.dp),
                        text = state.cityName,
                        fontSize = 48.sp,
                        lineHeight = 56.sp,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        maxLines = 2
                    )

                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp)
                            .padding(horizontal = 24.dp),
                        text = state.description,
                        fontSize = 24.sp,
                        lineHeight = 30.sp,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        maxLines = 2
                    )

                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 24.dp)
                            .padding(horizontal = 24.dp),
                        text = "Temperatura odczuwalna: ${state.feelTemp}°C",
                        fontSize = 16.sp,
                        color = Color.White,
                        maxLines = 1
                    )

                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 12.dp)
                            .padding(horizontal = 24.dp),
                        text = "Ciśnienie: ${state.pressure} hPa",
                        fontSize = 16.sp,
                        color = Color.White,
                        maxLines = 1
                    )

                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 12.dp)
                            .padding(horizontal = 24.dp),
                        text = "Wilgotność: ${state.humidity} %",
                        fontSize = 16.sp,
                        color = Color.White,
                        maxLines = 1
                    )
                }
            }
        }

        WeatherUiState.Loading -> {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }
}

private fun getBackgroundColorForCondition(weatherCondition: WeatherConditionUi) =
    when (weatherCondition) {
        WeatherConditionUi.Clear, WeatherConditionUi.Clouds -> ClearWeatherBackground
        else -> RainyWeatherBackground
    }

private fun getIconForCondition(weatherCondition: WeatherConditionUi) =
    when (weatherCondition) {
        WeatherConditionUi.Clear -> R.drawable.ic_sun
        WeatherConditionUi.Clouds, WeatherConditionUi.Unspecified -> R.drawable.ic_cloud
        WeatherConditionUi.Rain -> R.drawable.ic_rain
        WeatherConditionUi.Snow -> R.drawable.ic_snow
    }

private fun getTempColor(temp: Int) =
    when {
        temp < 10 -> ColdTemp
        temp < 20 -> NormalTemp
        else -> HotTemp
    }