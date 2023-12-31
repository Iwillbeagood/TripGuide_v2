package com.jun.tripguide_v2.core.data.repository.tourapi

import com.jun.tripguide_v2.core.model.Tourist

interface KeywordRepository {

    suspend fun getTouristList(
        queryParams: Map<String, String>,
        keyword: String
    ): List<Tourist>
}