package com.jun.tripguide_v2.core.data.api

import com.jun.tripguide_v2.core.data.api.model.areabaselist.AreaBaseListResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface TourAreaBaseListApi {
    @GET("areaBasedList1")
    suspend fun getAreaBaseList(
        @QueryMap queryParams: Map<String, String>,
        @Query("pageNo") pageNo: String,
        @Query("arrange") arrange: String,
        @Query("areaCode") areaCode: String
    ): AreaBaseListResponse

    @GET("areaBasedList1")
    suspend fun getSigunguBaseList(
        @QueryMap queryParams: Map<String, String>,
        @Query("pageNo") pageNo: String,
        @Query("arrange") arrange: String,
        @Query("areaCode") areaCode: String,
        @Query("sigunguCode") sigunguCode: String
    ): AreaBaseListResponse

    @GET("areaBasedList1")
    suspend fun getAreaBaseListWithType(
        @QueryMap queryParams: Map<String, String>,
        @Query("pageNo") pageNo: String,
        @Query("arrange") arrange: String,
        @Query("areaCode") areaCode: String,
        @Query("contentTypeId") contentType: String
    ): AreaBaseListResponse

    @GET("areaBasedList1")
    suspend fun getSigunguBaseListWithType(
        @QueryMap queryParams: Map<String, String>,
        @Query("pageNo") pageNo: String,
        @Query("arrange") arrange: String,
        @Query("areaCode") areaCode: String,
        @Query("sigunguCode") sigunguCode: String,
        @Query("contentTypeId") contentType: String
    ): AreaBaseListResponse
}