package com.jun.tripguide_v2.core.model

import java.time.Duration
import java.time.LocalDateTime

data class Tourist(
    val id: String = "",
    val title: String = "",
    val address: String = "",
    val type: String = "",
    val firstImage: String = "",
    val firstImage2: String = "",
    val mapX: Double = 0.0,
    val mapY: Double = 0.0,
    val arrivalDateTime: LocalDateTime = LocalDateTime.now(),
    val departureTime: LocalDateTime = LocalDateTime.now(),
    val stayDuration: Long = Duration.between(arrivalDateTime, departureTime).toMinutes(),
    val isSelected: Boolean = false
)
