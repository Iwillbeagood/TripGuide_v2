package com.jun.tripguide_v2.core.data_api.repository.tourapi

import com.jun.tripguide_v2.core.model.Tourist

interface KeywordRepository {

    suspend fun getTouristList(
        keyword: String
    ): List<Tourist>
}