package com.jun.tripguide_v2.core.data.mapper

import com.jun.tripguide_v2.core.data.api.kakaoapi.model.kakaokeyword.Document
import com.jun.tripguide_v2.core.model.Address

fun Document.toData(): Address = Address(
    id = this.id.toInt(),
    address = this.roadAddressName,
    name = this.placeName,
    x = this.x,
    y = this.y
)