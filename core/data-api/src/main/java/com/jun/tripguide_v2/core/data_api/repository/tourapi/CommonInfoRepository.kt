package com.jun.tripguide_v2.core.data_api.repository.tourapi

import com.jun.tripguide_v2.core.model.tourApi.CommonInfo

interface CommonInfoRepository {

    suspend fun getCommonInfo(
        contentId: String,
    ): CommonInfo
}