package com.jun.tripguide_v2.core.data.repository.tourapi

import com.jun.tripguide_v2.core.model.tourApi.AreaCode

interface AreaCodeRepository {

    suspend fun getAreaCode(
        queryParams: Map<String, String>,
        areaCode: String?
    ): List<AreaCode>
}
