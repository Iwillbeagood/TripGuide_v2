package com.jun.tripguide_v2.core.model.tourApi

data class LocationBasedTourist(
    val id: String,
    val dist: Double,
    val title: String,
    val address: String,
    val type: String,
    val firstImage: String,
    val firstImage2: String,
    val mapX: Double,
    val mapY: Double,
    val isSelected: Boolean = false
)
