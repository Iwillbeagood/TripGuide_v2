package com.jun.tripguide_v2.core.data.mapper

import com.jun.tripguide_v2.core.data.api.tourapi.model.location_tourist.LocationTouristItem
import com.jun.tripguide_v2.core.model.tourApi.LocationBasedTourist

fun LocationTouristItem.toLocationBasedTourist() = LocationBasedTourist(
    id = this.contentid,
    dist = dist,
    title = this.title,
    address = this.addr1,
    type = this.contenttypeid,
    firstImage = this.firstimage,
    firstImage2 = this.firstimage2,
    mapX = this.mapx,
    mapY = this.mapy
)