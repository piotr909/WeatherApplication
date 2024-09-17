package com.piotrapps.weatherapplication.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.piotrapps.weatherapplication.list.model.CitiesListUiState

@Composable
fun CitiesListScreen(
    navController: NavController,
    viewModel: CitiesViewModel = hiltViewModel<CitiesViewModel>()
) {
    var text by rememberSaveable { mutableStateOf("") }
    val citiesListUiState by viewModel.citiesListUiState.collectAsState()

    Column {
        TextField(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
            value = text,
            placeholder = {
                Text(text = "Wpisz nazwę miasta")
            },
            onValueChange = {
                if (it.all { it.isLetter() || it in " ąćęłńóśźż" }) {
                    text = it
                    if (it.isNotBlank()) {
                        viewModel.searchCity(it)
                    } else {
                        viewModel.clearSearch()
                    }
                }

            })

        when (val state = citiesListUiState) {
            is CitiesListUiState.Error -> {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 48.dp),
                    text = "Coś poszło nie tak, spróbuj ponownie",
                    fontSize = 20.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center,
                    maxLines = 1
                )
            }

            is CitiesListUiState.QueryResult -> {
                LazyColumn {
                    items(state.cities) { city ->
                        Text(text = "${city.name}, ${city.countryCode}",
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    navController.navigate("weatherDetails/${city.name}/${city.countryCode}")
                                }
                                .padding(16.dp)
                        )
                    }
                }
            }

            is CitiesListUiState.History -> {
                if (state.cities.isNotEmpty()) {
                    LazyColumn {
                        items(state.cities) { city ->
                            Text(text = "${city.name}, ${city.countryCode}",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        navController.navigate("weatherDetails/${city.name}/${city.countryCode}")
                                    }
                                    .padding(16.dp)
                            )
                        }
                    }
                } else {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 48.dp)
                            .padding(horizontal = 24.dp),
                        text = "Brak historii, spróbuj wyszukać miasto",
                        fontSize = 20.sp,
                        color = Color.Gray,
                        textAlign = TextAlign.Center
                    )
                }
            }

            null -> {}
        }
    }
}