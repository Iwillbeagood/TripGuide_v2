package com.jun.tripguide_v2.core.data.api

import com.jun.tripguide_v2.core.data.api.model.KakaoKeywordResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface KakaoKeywordApi {
    @GET("/v2/local/search/keyword.json")
    suspend fun getKakaoKeyword(
        @Header("Authorization") key: String,
        @Query("query") address: String
    ): List<KakaoKeywordResponse>
}
