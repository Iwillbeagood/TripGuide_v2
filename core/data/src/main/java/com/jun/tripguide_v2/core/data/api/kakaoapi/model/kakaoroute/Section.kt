package com.jun.tripguide_v2.core.data.api.kakaoapi.model.kakaoroute

import kotlinx.serialization.Serializable

@Serializable
data class Section(
    val bound: Bound,
    val distance: Int,
    val duration: Int,
    val guides: List<Guide>,
    val roads: List<Road>
)