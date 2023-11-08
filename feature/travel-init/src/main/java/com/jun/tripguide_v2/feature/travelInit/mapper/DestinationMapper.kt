package com.jun.tripguide_v2.feature.travelInit.mapper

import com.jun.tripguide_v2.core.model.DestinationCode

fun String?.toDestinationCode(): DestinationCode? {
    if (isNullOrBlank()) return null
    val (areaCode, sigunguCode, destinationString) = split("/")
    return DestinationCode(areaCode, sigunguCode, destinationString)
}