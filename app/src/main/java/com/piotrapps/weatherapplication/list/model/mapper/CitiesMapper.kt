package com.piotrapps.weatherapplication.list.model.mapper

import com.piotrapps.domain.model.City

fun City.toUi() = com.piotrapps.weatherapplication.list.model.City(
    this.name,
    this.countryCode
)