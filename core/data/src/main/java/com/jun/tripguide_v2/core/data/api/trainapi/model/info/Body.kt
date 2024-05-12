package com.jun.tripguide_v2.core.data.api.trainapi.model.info

import kotlinx.serialization.Serializable

@Serializable
data class Body(
    val items: Items = Items(emptyList()),
    val numOfRows: Int,
    val pageNo: Int,
    val totalCount: Int
)

