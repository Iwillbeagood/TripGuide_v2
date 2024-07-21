package com.jun.tripguide_v2.core.data_api.repository.tourapi

import com.jun.tripguide_v2.core.model.Tourist

interface TouristsRepository {

    suspend fun getTouristsApi(
        pageNo: String,
        arrange: String,
        areaCode: String,
        sigunguCode: String?,
        contentType: String?
    ): List<Tourist>
}