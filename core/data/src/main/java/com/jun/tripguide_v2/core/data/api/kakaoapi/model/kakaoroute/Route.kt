package com.jun.tripguide_v2.core.data.api.kakaoapi.model.kakaoroute

import kotlinx.serialization.Serializable

@Serializable
data class Route(
    val result_code: Int,
    val result_msg: String,
    val sections: List<Section>,
    val summary: Summary
)