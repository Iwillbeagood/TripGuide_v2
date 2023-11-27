package com.jun.tripguide_v2.core.model

data class Tourist(
    val id: String,
    val title: String,
    val address: String,
    val type: String,
    val firstImage: String,
    val firstImage2: String,
    val mapX: Double,
    val mapY: Double,
    val isSelected: Boolean = false
)
