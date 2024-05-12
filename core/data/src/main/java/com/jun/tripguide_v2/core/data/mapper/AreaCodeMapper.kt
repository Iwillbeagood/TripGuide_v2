package com.jun.tripguide_v2.core.data.mapper

import com.jun.tripguide_v2.core.data.api.tourapi.model.areacode.AreaCodeItem
import com.jun.tripguide_v2.core.model.tourApi.AreaCode

fun AreaCodeItem.toData(): AreaCode = AreaCode(
    code = this.code,
    name = this.name,
    isSelected = false
)