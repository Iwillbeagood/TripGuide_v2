package com.jun.tripguide_v2.core.data.repository.tourapi

import com.jun.tripguide_v2.core.data.api.tourapi.TourFestivalApi
import com.jun.tripguide_v2.core.data.mapper.toFestival
import com.jun.tripguide_v2.core.data_api.repository.tourapi.FestivalRepository
import com.jun.tripguide_v2.core.model.tourApi.Festival
import javax.inject.Inject

class FestivalRepositoryImpl @Inject constructor(
    private val tourFestivalApi: TourFestivalApi
): FestivalRepository {

    override suspend fun getFestivals(pageNo: Int, eventStartDate: String): List<Festival> {
        return tourFestivalApi.getFestivals(
            pageNo = pageNo,
            eventStartDate = eventStartDate).response.body.items.item.map { it.toFestival() }
    }
}