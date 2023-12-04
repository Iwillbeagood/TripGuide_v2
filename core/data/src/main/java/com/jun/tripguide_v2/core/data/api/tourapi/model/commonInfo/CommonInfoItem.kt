package com.jun.tripguide_v2.core.data.api.tourapi.model.commonInfo

import kotlinx.serialization.Serializable

@Serializable
data class CommonInfoItem(
    val addr1: String,
    val contenttypeid: String,
    val firstimage: String,
    val mapx: String,
    val mapy: String,
    val overview: String,
    val title: String,
)