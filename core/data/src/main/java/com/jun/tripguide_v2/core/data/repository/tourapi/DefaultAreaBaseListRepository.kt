package com.jun.tripguide_v2.core.data.repository.tourapi

import com.jun.tripguide_v2.core.data.api.TourAreaBaseListApi
import com.jun.tripguide_v2.core.data.mapper.toData
import com.jun.tripguide_v2.core.model.Tourist
import javax.inject.Inject

class DefaultAreaBaseListRepository @Inject constructor(
    private val tourAreaBaseListApi: TourAreaBaseListApi
) : AreaBaseListRepository {

    override suspend fun getAreaBaseList(
        queryParams: Map<String, String>,
        pageNo: String,
        arrange: String,
        areaCode: String
    ): List<Tourist> {
        return tourAreaBaseListApi.getAreaBaseList(
            queryParams = queryParams,
            pageNo = pageNo,
            arrange = arrange,
            areaCode = areaCode
        ).response.body.items.item.map {
            it.toData()
        }
    }

    override suspend fun getSigunguBaseList(
        queryParams: Map<String, String>,
        pageNo: String,
        arrange: String,
        areaCode: String,
        sigunguCode: String
    ): List<Tourist> {
        return tourAreaBaseListApi.getSigunguBaseList(
            queryParams = queryParams,
            pageNo = pageNo,
            arrange = arrange,
            areaCode = areaCode,
            sigunguCode = sigunguCode
        ).response.body.items.item.map {
            it.toData()
        }
    }

    override suspend fun getAreaBaseListWithType(
        queryParams: Map<String, String>,
        pageNo: String,
        arrange: String,
        areaCode: String,
        contentType: String
    ): List<Tourist> {
        return tourAreaBaseListApi.getAreaBaseListWithType(
            queryParams = queryParams,
            pageNo = pageNo,
            arrange = arrange,
            areaCode = areaCode,
            contentType = contentType
        ).response.body.items.item.map {
            it.toData()
        }
    }

    override suspend fun getSigunguBaseListWithType(
        queryParams: Map<String, String>,
        pageNo: String,
        arrange: String,
        areaCode: String,
        sigunguCode: String,
        contentType: String
    ): List<Tourist> {
        return tourAreaBaseListApi.getSigunguBaseListWithType(
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