package com.jun.tripguide_v2.core.data.repository.tourapi

import com.jun.tripguide_v2.core.data.api.tourapi.TourDetailIntroApi
import com.jun.tripguide_v2.core.data.mapper.toDetails
import com.jun.tripguide_v2.core.data_api.tourapi.DetailIntroRepository
import com.jun.tripguide_v2.core.model.tourApi.DetailIntro
import javax.inject.Inject

class DetailIntroRepositoryImpl @Inject constructor(
    private val detailIntroApi: TourDetailIntroApi
) : DetailIntroRepository {

    override suspend fun getDetailIntro(
        contentId: String,
        contentType: String
    ): List<DetailIntro> {
        return detailIntroApi.getDetailIntro(
            contentId, contentType
        ).response.body.items.item.first().toDetails()
    }
}