package com.jun.tripguide_v2.core.data.api.trainapi.model.station

import kotlinx.serialization.Serializable

@Serializable
data class Items(
    val item: List<StationItem>
)