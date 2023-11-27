package com.jun.tripguide_v2.core.data.api.kakaoapi.model.kakaoroute

import kotlinx.serialization.Serializable

@Serializable
data class Road(
    val distance: Int,
    val duration: Int,
    val name: String,
    val traffic_speed: Double,
    val traffic_state: Int,
    val vertexes: List<Double>
)