package com.jun.tripguide_v2.core.model

data class Route(
    val orderNum: Int = 0,
    val plusCode: String = "",
    val travelId: String,
    val title: String,
    val address: String = "",
    val typeId: String = "",
    val firstImage: String = "",
    val mapX: String,
    val mapY: String,
    val startTime: Long = 0,
    val endTime: Long = 0
)
