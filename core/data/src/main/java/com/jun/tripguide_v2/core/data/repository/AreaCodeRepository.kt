package com.jun.tripguide_v2.core.data.repository

import com.jun.tripguide_v2.core.model.AreaCode

interface AreaCodeRepository {

    suspend fun getAreaCode(
        queryParams: Map<String, String>,
        areaCode: String
    ): List<AreaCode>

    suspend fun getDefaultAreaCode(
        queryParams: Map<String, String>
    ): List<AreaCode>
}
