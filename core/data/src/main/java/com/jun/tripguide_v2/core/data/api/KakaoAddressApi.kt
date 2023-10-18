package com.jun.tripguide_v2.core.data.api

import com.jun.tripguide_v2.core.data.api.model.KakaoAddressResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface KakaoAddressApi {
    @GET("v2/local/search/address.json")
    suspend fun getKakaoAddress(
        @Header("Authorization") key: String,
        @Query("query") address: String
    ): List<KakaoAddressResponse>
}

