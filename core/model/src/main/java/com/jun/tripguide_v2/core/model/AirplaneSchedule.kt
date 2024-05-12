package com.jun.tripguide_v2.core.model

import java.time.LocalTime

data class AirplaneSchedule(
    val startCity: String,
    val startTime: LocalTime,
    val arrivalCity: String,
    val arrivalTime: LocalTime
)