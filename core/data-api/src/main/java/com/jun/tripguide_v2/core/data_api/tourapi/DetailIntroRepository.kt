package com.jun.tripguide_v2.core.data_api.tourapi

import com.jun.tripguide_v2.core.model.tourApi.DetailIntro

interface DetailIntroRepository {

    suspend fun getDetailIntro(
        contentId: String,
        contentType: String,
    ): List<DetailIntro>
}