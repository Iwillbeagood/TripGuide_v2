package com.jun.tripguide_v2.core.data.mapper

import com.jun.tripguide_v2.core.data.api.tourapi.model.stay.StayItem
import com.jun.tripguide_v2.core.model.tourApi.Stay

fun StayItem.toStay() = Stay(
    title = title,
    address = addr1,
    firstImage = firstimage,
    mapX = mapx, mapY = mapy,
    tel = tel
)