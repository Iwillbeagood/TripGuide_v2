package com.jun.tripguide_v2.core.data.mapper

import com.jun.tripguide_v2.core.data.api.tourapi.model.commonInfo.CommonInfoItem
import com.jun.tripguide_v2.core.model.tourApi.CommonInfo

fun CommonInfoItem.toCommonInfo() = CommonInfo(
    addr1,
    contenttypeid,
    firstimage,
    mapx,
    mapy,
    overview.htmlToString(),
    title
)
