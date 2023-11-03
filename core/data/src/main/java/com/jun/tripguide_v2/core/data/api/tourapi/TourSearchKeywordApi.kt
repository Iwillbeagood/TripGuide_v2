package com.jun.tripguide_v2.core.data.api.tourapi

import com.jun.tripguide_v2.core.data.api.tourapi.model.tourist.TouristResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface TourSearchKeywordApi {

    @GET("searchKeyword1")
    suspend fun getTouristList(
        @QueryMap queryParams: Map<String, String>,
        @Query("keyword") keyword: String
    ): TouristResponse
}
