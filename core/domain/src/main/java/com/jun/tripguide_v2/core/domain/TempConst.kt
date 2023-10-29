package com.jun.tripguide_v2.core.domain

object TempConst {
    const val TOUR_API_KEY = "LUjHE2JtNIM0j7H1yjIJnSkVhIS6p6I6R0y5F235iEiBQL9it8MXwm6mjNUFYGbnDpVFsqLgeYnIqcMNF83ilg=="

    const val KAKAO_API_KEY = "KakaoAK 666dd8d84bdf766d402bd0f500e08a4d"

    val queryParams = mutableMapOf(
        "numOfRows" to "30",
        "MobileOS" to "AND",
        "MobileApp" to "TripGuide_v2",
        "serviceKey" to TOUR_API_KEY,
        "_type" to "json"
    )

    val baseListQueryParams = mutableMapOf(
        "numOfRows" to "10",
        "MobileOS" to "AND",
        "MobileApp" to "TripGuide_v2",
        "serviceKey" to TOUR_API_KEY,
        "_type" to "json"
    )
}