package com.jun.tripguide_v2.core.data.api.airplaneapi.model

import kotlinx.serialization.Serializable

@Serializable
data class AirplaneScheduleItem(
    val startcity: String,
    val domesticStartTime: String,
    val arrivalcity: String,
    val domesticArrivalTime: String
)