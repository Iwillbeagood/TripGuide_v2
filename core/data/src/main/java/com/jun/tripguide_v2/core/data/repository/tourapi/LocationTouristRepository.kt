package com.jun.tripguide_v2.core.data.repository.tourapi

import com.jun.tripguide_v2.core.model.tourApi.LocationBasedTourist

interface LocationTouristRepository {

    suspend fun getLocationBasedTourists(
        pageNo: Int,
        mapX: Double,
        mapY: Double
    ): List<LocationBasedTourist>
}