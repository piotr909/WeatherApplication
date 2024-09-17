package com.piotrapps.weatherapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.piotrapps.weatherapplication.details.WeatherScreen
import com.piotrapps.weatherapplication.list.CitiesListScreen
import com.piotrapps.weatherapplication.ui.theme.WeatherApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherApplicationTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "citiesDist") {
                    composable("citiesDist") { CitiesListScreen(navController) }
                    composable("weatherDetails/{countryName}/{countryCode}") { backStackEntry ->
                        val countryName = backStackEntry.arguments?.getString("countryName")
                        val countryCode = backStackEntry.arguments?.getString("countryCode")

                        WeatherScreen(countryName, countryCode)
                    }
                }
            }
        }
    }
}
