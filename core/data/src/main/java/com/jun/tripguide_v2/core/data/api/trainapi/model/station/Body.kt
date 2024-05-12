package com.jun.tripguide_v2.core.data.api.trainapi.model.station

import kotlinx.serialization.Serializable

@Serializable
data class Body(
    val items: Items,
    val numOfRows: Int,
    val pageNo: Int,
    val totalCount: Int
)