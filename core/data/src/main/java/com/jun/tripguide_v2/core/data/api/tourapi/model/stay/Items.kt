package com.jun.tripguide_v2.core.data.api.tourapi.model.stay

import kotlinx.serialization.Serializable

@Serializable
data class Items(
    val item: List<StayItem>
)