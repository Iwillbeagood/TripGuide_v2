package com.jun.tripguide_v2.core.data.api.kakaoapi.model.kakaoroute

import kotlinx.serialization.Serializable

@Serializable
data class Bound(
    val max_x: Double,
    val max_y: Double,
    val min_x: Double,
    val min_y: Double
)