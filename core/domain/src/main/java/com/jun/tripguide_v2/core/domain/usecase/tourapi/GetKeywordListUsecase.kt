package com.jun.tripguide_v2.core.domain.usecase.tourapi

import com.jun.tripguide_v2.core.data.repository.tourapi.KeywordRepository
import com.jun.tripguide_v2.core.domain.Const.queryParams
import com.jun.tripguide_v2.core.model.Tourist
import javax.inject.Inject

class GetKeywordListUsecase @Inject constructor(
    private val keywordRepository: KeywordRepository
) {

    suspend operator fun invoke(
        keyword: String,
    ): List<Tourist> {
        return keywordRepository.getTouristList(
            queryParams = queryParams,
            keyword = keyword
        )
    }
}