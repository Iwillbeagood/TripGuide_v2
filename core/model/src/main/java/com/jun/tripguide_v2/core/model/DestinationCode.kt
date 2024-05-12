package com.jun.tripguide_v2.core.model

import com.jun.tripguide_v2.core.model.tourApi.AreaCode

data class DestinationCode(
    val areaCode: AreaCode,
    val sigunguCode: AreaCode
) {
    val destinationString get() = if (areaCode != sigunguCode) {
        "${areaCode.name} ${sigunguCode.name}"
    } else {
        areaCode.name
    }
}