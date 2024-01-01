package com.jun.tripguide_v2.core.data.mapper

import com.jun.tripguide_v2.core.data.api.tourapi.model.festival.FestivalItem
import com.jun.tripguide_v2.core.model.tourApi.Festival

fun FestivalItem.toFestival() = Festival(
    id = contentid,
    title = title,
    address = addr1,
    eventStartDate = eventstartdate,
    eventEndDate = eventenddate,
    firstImage = firstimage,
    mapX = mapx,
    mapY = mapy,
    tel = tel
)