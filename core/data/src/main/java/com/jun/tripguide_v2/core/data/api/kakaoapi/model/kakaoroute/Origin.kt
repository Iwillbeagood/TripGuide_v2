package com.jun.tripguide_v2.core.data.api.kakaoapi.model.kakaoroute

import kotlinx.serialization.Serializable

@Serializable
data class Origin(
    val name: String,
    val x: Double,
    val y: Double
)