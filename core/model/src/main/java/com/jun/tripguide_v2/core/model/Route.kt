package com.jun.tripguide_v2.core.model

import java.time.LocalTime

data class Route(
    val id: Long? = null,
    val orderNum: Int = 0,
    val travelId: String,
    val title: String,
    val address: String = "",
    val type: String = "",
    val firstImage: String = "",
    val mapX: Double,
    val mapY: Double,
    val time: LocalTime = LocalTime.of(0, 0),
    val day: Int = 1,
    val isSelected: Boolean = false,
    val isBeforeRouteSelected: Boolean = false,
    val isFirst: Boolean = false,
    val isLast: Boolean = false
)
