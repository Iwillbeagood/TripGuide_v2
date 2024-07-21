package com.jun.tripguide_v2.core.data_api.tourapi

import com.jun.tripguide_v2.core.model.tourApi.AreaCode

interface AreaCodeRepository {

    suspend fun getAreaCode(
        areaCode: String?
    ): List<AreaCode>
}
