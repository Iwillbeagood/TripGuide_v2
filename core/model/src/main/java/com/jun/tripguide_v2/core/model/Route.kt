package com.jun.tripguide_v2.core.model

data class Route(
    val orderNum: Int,
    val travelId: String,
    val title: String,
    val address: String,
    val typeId: String,
    val firstImage: String,
    val mapX: String,
    val mapY: String,
    val startTime: Long,
    val endTime: Long
)
