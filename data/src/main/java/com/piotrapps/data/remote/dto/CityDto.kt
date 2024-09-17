package com.piotrapps.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CityDto(
    @SerializedName("name") val name: String,
    @SerializedName("local_names") val localNamesDto: LocalNamesDto?,
    @SerializedName("country") val country: String,
    @SerializedName("state") val state: String,
)

data class LocalNamesDto(
    @SerializedName("pl") val name: String?
)
