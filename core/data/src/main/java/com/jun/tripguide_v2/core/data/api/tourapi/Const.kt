package com.jun.tripguide_v2.core.data.api.tourapi

import com.jun.tripguide_v2.core.data.BuildConfig

object Const {
    val queryParams = mutableMapOf(
        "numOfRows" to "20",
        "MobileOS" to "AND",
        "MobileApp" to "TripGuide_v2",
        "serviceKey" to BuildConfig.OPEN_API_KEY,
        "_type" to "json"
    )
}