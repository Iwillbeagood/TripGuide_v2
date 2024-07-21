package com.jun.tripguide_v2.core.data.repository.tourapi

import com.jun.tripguide_v2.core.data.api.tourapi.TourStayApi
import com.jun.tripguide_v2.core.data.mapper.toStay
import com.jun.tripguide_v2.core.data_api.tourapi.StayRepository
import com.jun.tripguide_v2.core.model.tourApi.Stay
import javax.inject.Inject

class StayRepositoryImpl @Inject constructor(
    private val tourStayApi: TourStayApi
): StayRepository {

    override suspend fun getStays(pageNo: Int): List<Stay> {
        return tourStayApi.getStays(
            pageNo = pageNo).response.body.items.item.map { it.toStay() }
    }
}