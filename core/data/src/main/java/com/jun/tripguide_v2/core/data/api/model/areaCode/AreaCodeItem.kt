package com.jun.tripguide_v2.core.data.api.model.areaCode

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AreaCodeItem(
    @SerialName("code") val code: String,
    @SerialName("name") val name: String,
    @SerialName("rnum") val rnum: Int
)