package com.jun.tripguide_v2.core.domain

object TempConst {

    val queryParams = mutableMapOf(
        "numOfRows" to "20",
        "MobileOS" to "AND",
        "MobileApp" to "TripGuide_v2",
        "serviceKey" to BuildConfig.TOUR_API_KEY,
        "_type" to "json"
    )
}