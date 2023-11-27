package com.jun.tripguide_v2.core.data.api.kakaoapi.model.kakaoroute

import kotlinx.serialization.Serializable

@Serializable
data class Guide(
    val distance: Int,
    val duration: Int,
    val guidance: String,
    val name: String,
    val road_index: Int,
    val type: Int,
    val x: Double,
    val y: Double
)