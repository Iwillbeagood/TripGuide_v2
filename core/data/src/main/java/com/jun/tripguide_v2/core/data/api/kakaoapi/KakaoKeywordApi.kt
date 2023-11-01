package com.jun.tripguide_v2.core.data.api.kakaoapi

import com.jun.tripguide_v2.core.data.api.kakaoapi.model.kakaokeyword.KakaoKeywordResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface KakaoKeywordApi {
    @GET("/v2/local/search/keyword.json")
    suspend fun getKakaoLocalByKeyword(
        @Header("Authorization") key: String,
        @Query("query") address: String
    ): KakaoKeywordResponse
}
