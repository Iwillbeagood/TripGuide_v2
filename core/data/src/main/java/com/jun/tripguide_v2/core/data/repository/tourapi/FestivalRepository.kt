package com.jun.tripguide_v2.core.data.repository.tourapi

import com.jun.tripguide_v2.core.model.tourApi.Festival

interface FestivalRepository {

    suspend fun getFestivals(
        pageNo: Int,
        eventStartDate: String
    ): List<Festival>
}