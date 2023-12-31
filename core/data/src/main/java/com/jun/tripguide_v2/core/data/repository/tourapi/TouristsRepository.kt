package com.jun.tripguide_v2.core.data.repository.tourapi

import com.jun.tripguide_v2.core.model.Tourist

interface TouristsRepository {

    suspend fun getTouristsApi(
        queryParams: Map<String, String>,
        pageNo: String,
        arrange: String,
        areaCode: String,
        sigunguCode: String?,
        contentType: String?
    ): List<Tourist>
}