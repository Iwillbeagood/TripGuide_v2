package com.jun.tripguide_v2.core.data.repository.tourapi

import com.jun.tripguide_v2.core.model.Tourist

interface AreaBaseListRepository {

    suspend fun getAreaBaseList(
        queryParams: Map<String, String>,
        pageNo: String,
        arrange: String,
        areaCode: String
    ): List<Tourist>

    suspend fun getSigunguBaseList(
        queryParams: Map<String, String>,
        pageNo: String,
        arrange: String,
        areaCode: String,
        sigunguCode: String
    ): List<Tourist>

    suspend fun getAreaBaseListWithType(
        queryParams: Map<String, String>,
        pageNo: String,
        arrange: String,
        areaCode: String,
        contentType: String
    ): List<Tourist>

    suspend fun getSigunguBaseListWithType(
        queryParams: Map<String, String>,
        pageNo: String,
        arrange: String,
        areaCode: String,
        sigunguCode: String,
        contentType: String
    ): List<Tourist>
}