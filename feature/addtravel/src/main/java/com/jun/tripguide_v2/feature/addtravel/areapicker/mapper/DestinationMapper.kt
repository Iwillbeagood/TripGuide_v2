package com.jun.tripguide_v2.feature.addtravel.areapicker.mapper

import com.jun.tripguide_v2.core.model.DestinationCode

fun String?.toDestinationData(): DestinationCode? {
    if (isNullOrBlank()) return null
    val (areaCode, sigunguCode, destinationString) = split("/")
    return DestinationCode(areaCode, sigunguCode, destinationString)
}