package com.jun.tripguide_v2.core.data.mapper

import com.jun.tripguide_v2.core.data.api.model.areabaselist.AreaBaseItem
import com.jun.tripguide_v2.core.model.Tourist

fun AreaBaseItem.toData(): Tourist = Tourist(
    id = this.contentid,
    title = this.title,
    address = this.addr1,
    typeId = this.contenttypeid,
    firstImage = this.firstimage,
    firstImage2 = this.firstimage2,
    mapX = this.mapx,
    mapY = this.mapy
)