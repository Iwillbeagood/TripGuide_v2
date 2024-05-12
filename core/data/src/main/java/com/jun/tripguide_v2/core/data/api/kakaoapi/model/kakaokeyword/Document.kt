package com.jun.tripguide_v2.core.data.api.kakaoapi.model.kakaokeyword

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Document(
    @SerialName("place_name") val placeName: String,
    @SerialName("road_address_name") val roadAddressName: String,
    val id : String,
    val x: String,
    val y: String,
)