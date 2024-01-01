package com.jun.tripguide_v2.core.model

data class TrainStation(
    val stationId: String,
    val stationName: String,
    val isSelected: Boolean = false
)