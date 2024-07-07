package com.jun.tripguide_v2.core.data.api.tourapi

import com.jun.tripguide_v2.core.data.api.tourapi.model.detailIntro.DetailIntroResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface TourDetailIntroApi {
    @GET("detailIntro1")
    suspend fun getDetailIntro(
        @Query("contentId") contentId: String,
        @Query("contentTypeId") contentType: String,
        @QueryMap queryParams: Map<String, String> = Const.queryParams,
    ): DetailIntroResponse
}
