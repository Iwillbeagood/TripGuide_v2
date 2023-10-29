package com.jun.tripguide_v2.core.model

data class Tourist(
    val id: String,
    val title: String,
    val address: String,
    val typeId: String,
    val firstImage: String,
    val firstImage2: String,
    val mapX: String,
    val mapY: String,
    val isSelected: Boolean = false
)
