package com.jun.tripguide_v2.core.data_api.repository.tourapi

import com.jun.tripguide_v2.core.model.tourApi.Stay

interface StayRepository {

    suspend fun getStays(
        pageNo: Int
    ): List<Stay>
}