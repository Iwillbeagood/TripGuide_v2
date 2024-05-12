package com.jun.tripguide_v2.core.data.repository.tourapi

import com.jun.tripguide_v2.core.model.tourApi.DetailIntro

interface DetailIntroRepository {

    suspend fun getDetailIntro(
        queryParams: Map<String, String>,
        contentId: String,
        contentType: String,
    ): List<DetailIntro>
}