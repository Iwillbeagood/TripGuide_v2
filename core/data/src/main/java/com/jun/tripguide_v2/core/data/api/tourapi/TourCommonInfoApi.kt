package com.jun.tripguide_v2.core.data.api.tourapi

import com.jun.tripguide_v2.core.data.api.tourapi.model.commonInfo.CommonInfoResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface TourCommonInfoApi {
    @GET("detailCommon1")
    suspend fun getCommonInfo(
        @Query("contentId") contentId: String,
        @Query("defaultYN") defaultYN: String = "Y",
        @Query("firstImageYN") firstImageYN: String = "Y",
        @Query("addrinfoYN") addrinfoYN: String = "Y",
        @Query("mapinfoYN") mapinfoYN: String = "Y",
        @Query("overviewYN") overviewYN: String = "Y",
        @QueryMap queryParams: Map<String, String> = Const.queryParams,
    ): CommonInfoResponse
}
