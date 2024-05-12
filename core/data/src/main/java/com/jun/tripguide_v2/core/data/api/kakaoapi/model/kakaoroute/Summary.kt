package com.jun.tripguide_v2.core.data.api.kakaoapi.model.kakaoroute

import kotlinx.serialization.Serializable

@Serializable
data class Summary(
    val bound: Bound,
    val destination: Destination,
    val distance: Int,
    val duration: Int,
    val fare: Fare,
    val origin: Origin,
    val priority: String,
)