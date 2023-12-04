package com.jun.tripguide_v2.core.data.repository.tourapi

import com.jun.tripguide_v2.core.data.api.tourapi.OpenTouristsApi
import com.jun.tripguide_v2.core.data.mapper.toData
import com.jun.tripguide_v2.core.model.Tourist
import javax.inject.Inject

class TouristsRepositoryImpl @Inject constructor(
    private val openTouristsApi: OpenTouristsApi
) : TouristsRepository {

    override suspend fun getTouristsApi(
        queryParams: Map<String, String>,
        pageNo: String,
        arrange: String,
        areaCode: String,
        sigunguCode: String?,
        contentType: String?
    ): List<Tourist> {
        return openTouristsApi.getTourists(
            queryParams = queryParams,
            pageNo = pageNo,
            arrange = arrange,
            areaCode = areaCode,
            sigunguCode = sigunguCode,
            contentType = contentType
        ).response.body.items.item.map {
            it.toData()
        }
    }
}