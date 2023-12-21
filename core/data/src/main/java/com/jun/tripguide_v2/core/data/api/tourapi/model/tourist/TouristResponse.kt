package com.jun.tripguide_v2.core.data.api.tourapi.model.tourist

import com.jun.tripguide_v2.core.data.api.tourapi.model.location_tourist.Response
import kotlinx.serialization.Serializable

@Serializable
data class TouristResponse(
    val response: Response
)