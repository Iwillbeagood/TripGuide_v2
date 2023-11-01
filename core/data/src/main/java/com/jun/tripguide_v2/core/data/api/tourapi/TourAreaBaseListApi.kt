package com.jun.tripguide_v2.core.data.api.tourapi

import com.jun.tripguide_v2.core.data.api.tourapi.model.tourist.TouristResponse
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
    ): TouristResponse

    @GET("areaBasedList1")
    suspend fun getSigunguBaseList(
        @QueryMap queryParams: Map<String, String>,
        @Query("pageNo") pageNo: String,
        @Query("arrange") arrange: String,
        @Query("areaCode") areaCode: String,
        @Query("sigunguCode") sigunguCode: String
    ): TouristResponse

    @GET("areaBasedList1")
    suspend fun getAreaBaseListWithType(
        @QueryMap queryParams: Map<String, String>,
        @Query("pageNo") pageNo: String,
        @Query("arrange") arrange: String,
        @Query("areaCode") areaCode: String,
        @Query("contentTypeId") contentType: String
    ): TouristResponse

    @GET("areaBasedList1")
    suspend fun getSigunguBaseListWithType(
        @QueryMap queryParams: Map<String, String>,
        @Query("pageNo") pageNo: String,
        @Query("arrange") arrange: String,
        @Query("areaCode") areaCode: String,
        @Query("sigunguCode") sigunguCode: String,
        @Query("contentTypeId") contentType: String
    ): TouristResponse
}