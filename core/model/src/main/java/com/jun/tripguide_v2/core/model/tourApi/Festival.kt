package com.jun.tripguide_v2.core.model.tourApi

import com.jun.tripguide_v2.core.model.ContentType

data class Festival(
    val id: String,
    val title: String,
    val type: ContentType = ContentType.EventFestival,
    val address: String,
    val eventEndDate: String,
    val eventStartDate: String,
    val firstImage: String,
    val mapX: String,
    val mapY: String,
    val tel: String
)
