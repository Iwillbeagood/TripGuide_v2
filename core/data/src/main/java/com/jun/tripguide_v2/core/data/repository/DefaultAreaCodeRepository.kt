package com.jun.tripguide_v2.core.data.repository

import com.jun.tripguide_v2.core.data.api.TourAreaCodeApi
import com.jun.tripguide_v2.core.data.mapper.toData
import com.jun.tripguide_v2.core.model.AreaCode
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class DefaultAreaCodeRepository @Inject constructor(
    private val tourAreaCodeApi: TourAreaCodeApi
) : AreaCodeRepository {

    override suspend fun getAreaCode(
        queryParams: Map<String, String>,
        areaCode: String
    ): List<AreaCode> {
        return mutableListOf(AreaCode("0", "전체")).apply {
            addAll(tourAreaCodeApi.getAreaCode(queryParams, areaCode).response.body.items.item.map {
                it.toData()
            })
        }
    }

    override suspend fun getDefaultAreaCode(
        queryParams: Map<String, String>
    ): List<AreaCode> {
        return mutableListOf(AreaCode("0", "전체")).apply {
            addAll(tourAreaCodeApi.getDefaultAreaCode(queryParams).response.body.items.item.map {
                it.toData()
            })
        }
    }
}