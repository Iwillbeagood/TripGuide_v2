package com.jun.tripguide_v2.core.data.api.tourapi.model.tourist

import com.jun.tripguide_v2.core.data.api.tourapi.model.location_tourist.Items
import kotlinx.serialization.Serializable

@Serializable
data class Body(
    val items: Items,
    val numOfRows: Int,
    val pageNo: Int,
    val totalCount: Int
)