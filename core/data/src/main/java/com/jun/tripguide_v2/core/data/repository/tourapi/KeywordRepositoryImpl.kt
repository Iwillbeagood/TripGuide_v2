package com.jun.tripguide_v2.core.data.repository.tourapi

import com.jun.tripguide_v2.core.data.api.tourapi.TourSearchKeywordApi
import com.jun.tripguide_v2.core.data.mapper.toData
import com.jun.tripguide_v2.core.model.Tourist
import javax.inject.Inject

class KeywordRepositoryImpl @Inject constructor(
    private val keywordApi: TourSearchKeywordApi
): KeywordRepository {

    override suspend fun getTouristList(
        queryParams: Map<String, String>,
        keyword: String
    ): List<Tourist> {
        return keywordApi.getTouristList(
            queryParams, keyword
        ).response.body.items.item.map {
            it.toData()
        }
    }
}