package com.jun.tripguide_v2.core.data.repository.tourapi

import com.jun.tripguide_v2.core.data.api.tourapi.TourCommonInfoApi
import com.jun.tripguide_v2.core.data.mapper.toCommonInfo
import com.jun.tripguide_v2.core.model.tourApi.CommonInfo
import javax.inject.Inject

class CommonInfoRepositoryImpl @Inject constructor(
    private val commonInfoApi: TourCommonInfoApi
) : CommonInfoRepository {

    override suspend fun getCommonInfo(
        queryParams: Map<String, String>,
        contentId: String
    ): CommonInfo {
        return commonInfoApi.getCommonInfo(
            queryParams,
            contentId
        ).response.body.items.item.first().toCommonInfo()
    }
}