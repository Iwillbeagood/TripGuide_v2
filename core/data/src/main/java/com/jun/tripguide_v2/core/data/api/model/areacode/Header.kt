package com.jun.tripguide_v2.core.data.api.model.areacode

import kotlinx.serialization.Serializable

@Serializable
data class Header(
    val resultCode: String,
    val resultMsg: String
)