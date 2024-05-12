package com.jun.tripguide_v2.core.data.api.tourapi.model.festival

import kotlinx.serialization.Serializable

@Serializable
data class Response(
    val body: Body,
    val header: Header
)