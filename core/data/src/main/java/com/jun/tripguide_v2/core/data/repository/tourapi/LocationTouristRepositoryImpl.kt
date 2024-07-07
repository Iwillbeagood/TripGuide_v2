package com.jun.tripguide_v2.core.data.repository.tourapi

import com.jun.tripguide_v2.core.data.api.tourapi.TourLocationTouristApi
import com.jun.tripguide_v2.core.data.mapper.toLocationBasedTourist
import com.jun.tripguide_v2.core.model.tourApi.LocationBasedTourist
import javax.inject.Inject

class LocationTouristRepositoryImpl @Inject constructor(
    private val locationTouristApi: TourLocationTouristApi
) : LocationTouristRepository {

    override suspend fun getLocationBasedTourists(
        pageNo: Int,
        mapX: Double,
        mapY: Double
    ): List<LocationBasedTourist> {
        return locationTouristApi.getLocationBasedTourists(pageNo, mapX, mapY).response.body.items.item.map {
            it.toLocationBasedTourist()
        }
    }
}