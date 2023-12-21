package com.jun.tripguide_v2.core.data.api.tourapi.model.tourist

import com.jun.tripguide_v2.core.data.api.tourapi.model.location_tourist.Body
import com.jun.tripguide_v2.core.data.api.tourapi.model.location_tourist.Header
import kotlinx.serialization.Serializable

@Serializable
data class Response(
    val body: Body,
    val header: Header
)