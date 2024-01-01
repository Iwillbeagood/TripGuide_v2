package com.jun.tripguide_v2.core.data.api.airplaneapi.model

import kotlinx.serialization.Serializable

@Serializable
data class Body(
    val airplaneScheduleItems: List<AirplaneScheduleItem>
)