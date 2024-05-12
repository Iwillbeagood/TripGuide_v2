package com.jun.tripguide_v2.core.data.repository.tourapi

import com.jun.tripguide_v2.core.data.api.tourapi.TourAreaCodeApi
import com.jun.tripguide_v2.core.data.mapper.toData
import com.jun.tripguide_v2.core.model.tourApi.AreaCode
import javax.inject.Inject

internal class AreaCodeRepositoryImpl @Inject constructor(
    private val tourAreaCodeApi: TourAreaCodeApi
) : AreaCodeRepository {

    override suspend fun getAreaCode(
        queryParams: Map<String, String>,
        areaCode: String?
    ): List<AreaCode> {
        return mutableListOf(AreaCode("0", "전체")).apply {
            addAll(tourAreaCodeApi.getAreaCode(queryParams, areaCode).response.body.items.item.map {
                it.toData()
            })
        }
    }

}