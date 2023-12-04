package com.jun.tripguide_v2.core.data.api.tourapi.model.detailIntro

import kotlinx.serialization.Serializable

@Serializable
data class Body(
    val items: Items,
    val numOfRows: Int,
    val pageNo: Int,
    val totalCount: Int
)